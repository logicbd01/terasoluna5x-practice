/**
 * 
 */
package todo.domain.service.todo;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import todo.domain.model.Todo;
import todo.domain.repository.todo.TodoRepository;

/**
 * Todoサービス実装クラス
 * @author matsumoto
 */
@Service
@Transactional
public class TodoServiceImpl implements TodoService {

	/**
	 * 最大未完了TODO件数
	 */
	private static final long MAX_UNFINISHED_COUNT = 5;
	
	@Inject
	TodoRepository todoRepository;
	
	/* (non-Javadoc)
	 * @see todo.domain.service.todo.TodoService#findAll()
	 */
	@Override
	@Transactional(readOnly = true)
	public Collection<Todo> findAll() {
		// 全件取得
		return todoRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see todo.domain.service.todo.TodoService#create(todo.domain.model.Todo)
	 */
	@Override
	public Todo create(Todo todo) {

		// 業務条件チェック
		// 未完了件数取得
		long unfinishedCount = todoRepository.countByFinished(false);
		// 未完了件数が5件以上の場合、エラー
		if (unfinishedCount >= MAX_UNFINISHED_COUNT) {
			ResultMessages messages = ResultMessages.error();
			messages.add(ResultMessage.fromText("[E001] The count of un-finished Todo must not be over " + MAX_UNFINISHED_COUNT + "."));
			throw new BusinessException(messages);
		}

		// 足らない項目を作成
		todo.setTodoId(UUID.randomUUID().toString());
		todo.setCreatedAt(new Date());
		todo.setFinished(false);
		// データ登録
		todoRepository.create(todo);
		
		return todo;
	}

	/* (non-Javadoc)
	 * @see todo.domain.service.todo.TodoService#finish(java.lang.String)
	 */
	@Override
	public Todo finish(String todoId) {
		
		// 対象データを取得
		Todo todo = findOne(todoId);

		// 対象データがすでに完了している場合はエラー
		if (todo.isFinished()) {
			ResultMessages messages = ResultMessages.error();
			messages.add(ResultMessage.fromText("[E002] The requested Todo is already finished. (id=" + todoId + ""));
			throw new BusinessException(messages);
		}
		
		// 対象データの完了フラグを更新
		todo.setFinished(true);
		todoRepository.update(todo);
		
		return todo;
	}

	/* (non-Javadoc)
	 * @see todo.domain.service.todo.TodoService#delete(java.lang.String)
	 */
	@Override
	public void delete(String todoId) {

		// 対象情報取得
		Todo todo = findOne(todoId);
		// 削除
		todoRepository.delete(todo);
	}

	/**
	 * 1件取得
	 * @param todoId 対象ID
	 * @return TODO情報
	 */
	private Todo findOne(String todoId) {
		Todo todo = todoRepository.findOne(todoId);
		if (todo == null) {
			ResultMessages messages = ResultMessages.error();
			messages.add(ResultMessage.fromText("[E404] The requested Todo is not found. (id=" + todoId + ")"));
			throw new ResourceNotFoundException(messages);
		}
		return todo;
	}

}
