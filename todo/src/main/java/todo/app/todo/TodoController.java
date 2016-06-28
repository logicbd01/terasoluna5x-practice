/**
 * 
 */
package todo.app.todo;

import java.util.Collection;

import javax.inject.Inject;
import javax.validation.groups.Default;

import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import todo.app.todo.TodoForm.TodoCreate;
import todo.app.todo.TodoForm.TodoDelete;
import todo.app.todo.TodoForm.TodoFinish;
import todo.domain.model.Todo;
import todo.domain.service.todo.TodoService;

/**
 * Todoコントローラ
 * @author matsumoto
 */
@Controller
@RequestMapping("todo")
public class TodoController {

	/**
	 * Todoサービス
	 */
	@Inject
	TodoService todoService;

	/**
	 * Beanマッパー
	 */
	@Inject
	Mapper beanMapper;

	/**
	 * フォームの初期化
	 * @return Todoフォーム
	 */
	@ModelAttribute
	public TodoForm setUpForm() {
		TodoForm todoForm = new TodoForm();
		return todoForm;
	}

	/**
	 * 一覧表示
	 * @param model 画面返却値
	 * @return 遷移先パス
	 */
	@RequestMapping(value = "list")
	public String list(Model model) {
		Collection<Todo> todos = todoService.findAll();
		model.addAttribute("todos", todos);
		return "todo/list";
	}
	
	/**
	 * 登録
	 * @param todoForm 画面入力値フォーム
	 * @param bindingResult 入力チェック結果
	 * @param model 画面返却値
	 * @param redirectAttributes リダイレクト先返却値
	 * @return 遷移先パス
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Validated({Default.class, TodoCreate.class}) TodoForm todoForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

		// 入力チェックエラーの場合は一覧画面に遷移する
		if (bindingResult.hasErrors()) {
			return list(model);
		}
		
		// 入力フォームオブジェクトをモデルオブジェクトへ変換
		Todo todo = beanMapper.map(todoForm, Todo.class);

		try {
			// 登録処理実行
			todoService.create(todo);
		} catch (BusinessException e) {
			// 登録処理でエラーが発生した場合は一覧画面に遷移する
			model.addAttribute(e.getResultMessages());
			return list(model);
		}

		// 正常終了した場合は一覧表示画面へリダイレクト
		redirectAttributes.addFlashAttribute(ResultMessages.success().add(ResultMessage.fromText("Created successfully!")));
		return "redirect:/todo/list";
	}
	
	/**
	 * 完了
	 * @param form 画面入力値フォーム
	 * @param bindingResult 入力チェック結果
	 * @param model 画面返却値
	 * @param redirectAttributes リダイレクト先返却値
	 * @return 遷移先パス
	 */
	@RequestMapping(value = "finish", method=RequestMethod.POST)
	public String finish(@Validated({Default.class, TodoFinish.class}) TodoForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

		// 入力チェックエラーの場合は一覧画面に遷移する
		if (bindingResult.hasErrors()) {
			return list(model);
		}
		
		try {
			// 完了処理実行
			todoService.finish(form.getTodoId());
		} catch (BusinessException e) {
			// 完了処理でエラーが発生した場合は一覧画面に遷移する
			model.addAttribute(e.getResultMessages());
			return list(model);
		}
		
		// 正常終了した場合は一覧表示画面へリダイレクト
		redirectAttributes.addFlashAttribute(ResultMessages.success().add(ResultMessage.fromText("Finished successfully!")));
		return "redirect:/todo/list";
	}

	/**
	 * 削除
	 * @param form 画面入力値フォーム
	 * @param bindingResult 入力チェック結果
	 * @param model 画面返却値
	 * @param redirectAttributes リダイレクト先返却値
	 * @return 遷移先パス
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete (@Validated({Default.class, TodoDelete.class}) TodoForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

		// 入力チェックエラーの場合は一覧画面に遷移する
		if (bindingResult.hasErrors()) {
			return list(model);
		}
		
		try {
			// 削除処理実行
			todoService.delete(form.getTodoId());
		} catch (BusinessException e) {
			// 完了処理でエラーが発生した場合は一覧画面に遷移する
			model.addAttribute(e.getResultMessages());
			return list(model);
		}
		
		// 正常終了した場合は一覧表示画面へリダイレクト
		redirectAttributes.addFlashAttribute(ResultMessages.success().add(ResultMessage.fromText("Deleted successfully!")));
		return "redirect:/todo/list";
	}

}
