/**
 * 
 */
package todo.domain.repository.todo;

import java.util.Collection;

import todo.domain.model.Todo;

/**
 * Todoリポジトリインタフェース
 * @author matsumoto
 */
public interface TodoRepository {

	/**
	 * 1件取得
	 * @param todoId 取得するID
	 * @return 取得結果
	 */
	Todo findOne(String todoId);
	/**
	 * 全件取得
	 * @return 取得結果
	 */
	Collection<Todo> findAll();
	/**
	 * 1件登録
	 * @param todo 登録情報
	 */
	void create(Todo todo);
	/**
	 * 1件更新
	 * @param todo 更新情報
	 * @return 更新結果
	 */
	boolean update(Todo todo);
	/**
	 * 1件削除
	 * @param todo 削除情報
	 */
	void delete(Todo todo);
	/**
	 * 件数取得
	 * @param finished 完了フラグ
	 * @return 件数
	 */
	long countByFinished(boolean finished);
}
