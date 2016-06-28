/**
 * 
 */
package todo.domain.repository.todo;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import todo.domain.model.Todo;

/**
 * Todoリポジトリ実装クラス
 * @author matsumoto
 */
@Repository
public class TodoRepositoryImpl implements TodoRepository {

	/**
	 * データマップ, key = id, value = data
	 */
	private static final Map<String, Todo> TODO_MAP = new ConcurrentHashMap<String, Todo>();

	/* (non-Javadoc)
	 * @see todo.domain.repository.todo.TodoRepository#findOne(java.lang.String)
	 */
	@Override
	public Todo findOne(String todoId) {
		return TODO_MAP.get(todoId);
	}

	/* (non-Javadoc)
	 * @see todo.domain.repository.todo.TodoRepository#findAll()
	 */
	@Override
	public Collection<Todo> findAll() {
		return TODO_MAP.values();
	}

	/* (non-Javadoc)
	 * @see todo.domain.repository.todo.TodoRepository#create(todo.domain.model.Todo)
	 */
	@Override
	public void create(Todo todo) {
		TODO_MAP.put(todo.getTodoId(), todo);
	}

	/* (non-Javadoc)
	 * @see todo.domain.repository.todo.TodoRepository#update(todo.domain.model.Todo)
	 */
	@Override
	public boolean update(Todo todo) {
		TODO_MAP.put(todo.getTodoId(), todo);
		return true;
	}

	/* (non-Javadoc)
	 * @see todo.domain.repository.todo.TodoRepository#delete(todo.domain.model.Todo)
	 */
	@Override
	public void delete(Todo todo) {
		TODO_MAP.remove(todo.getTodoId());
	}

	/* (non-Javadoc)
	 * @see todo.domain.repository.todo.TodoRepository#countByFinished(boolean)
	 */
	@Override
	public long countByFinished(boolean finished) {
		long count = 0;
		for (Todo todo : TODO_MAP.values()) {
			if (finished == todo.isFinished()) {
				count++;
			}
		}
		return count;
	}

}
