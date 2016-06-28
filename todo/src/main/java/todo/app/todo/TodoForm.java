/**
 * 
 */
package todo.app.todo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Todoフォーム
 * @author matsumoto
 */
public class TodoForm implements Serializable {

	/**
	 * 登録入力チェックグループ化用インタフェース
	 * @author matumoto
	 */
	public static interface TodoCreate {};
	/**
	 * 完了入力チェックグループ化用インタフェース
	 * @author matsumoto
	 */
	public static interface TodoFinish {};
	/**
	 * 削除入力チェックグループ化用インタフェース
	 * @author matsumoto
	 */
	public static interface TodoDelete {};

	/**
	 * シリアルバージョンID
	 */
	private static final long serialVersionUID = -167352220177437781L;

	/**
	 * ID
	 */
	@NotNull(groups = {TodoFinish.class, TodoDelete.class})
	private String todoId;

	/**
	 * タイトル
	 */
	@NotNull(groups = {TodoCreate.class})
	@Size(min = 1, max = 30, groups = {TodoCreate.class})
	private String todoTitle;

	/**
	 * @return the todoTitle
	 */
	public String getTodoTitle() {
		return todoTitle;
	}

	/**
	 * @param todoTitle the todoTitle to set
	 */
	public void setTodoTitle(String todoTitle) {
		this.todoTitle = todoTitle;
	}

	/**
	 * @return the todoId
	 */
	public String getTodoId() {
		return todoId;
	}

	/**
	 * @param todoId the todoId to set
	 */
	public void setTodoId(String todoId) {
		this.todoId = todoId;
	}

}
