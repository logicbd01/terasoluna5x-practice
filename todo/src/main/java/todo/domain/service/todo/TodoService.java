/**
 * 
 */
package todo.domain.service.todo;

import java.util.Collection;

import todo.domain.model.Todo;

/**
 * Todoサービスインタフェース
 * @author matsumoto
 */
public interface TodoService {

	/**
	 * 全件取得
	 * @return 取得結果
	 */
	Collection<Todo> findAll();
	/**
	 * 登録
	 * @param todo Todo情報
	 * @return 登録情報
	 */
	Todo create(Todo todo);
	/**
	 * 完了
	 * @param todoId 対象ID
	 * @return 完了情報
	 */
	Todo finish(String todoId);
	/**
	 * 削除
	 * @param todoId 対象ID
	 */
	void delete(String todoId);
}
