/**
 * 
 */
package todo.domain.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Todoモデル
 * @author matsumoto
 */
public class Todo implements Serializable {

	/**
	 * シリアルバージョンID
	 */
	private static final long serialVersionUID = -3391054273020868964L;

	/**
	 * ID
	 */
	private String todoId;
	/**
	 * タイトル
	 */
	private String todoTitle;
	/**
	 * 完了フラグ
	 */
	private boolean finished;
	/**
	 * 作成日
	 */
	private Date createdAt;
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
	 * @return the finished
	 */
	public boolean isFinished() {
		return finished;
	}
	/**
	 * @param finished the finished to set
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
