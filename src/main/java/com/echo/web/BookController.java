package com.echo.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.echo.dto.AppointExcuetion;
import com.echo.dto.Result;
import com.echo.entity.Book;
import com.echo.entity.UserInfo;
import com.echo.service.BookService;

@Controller
@RequestMapping("/book") // url:/模块/资源/{id}/细分 /seckill/list
public class BookController {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model){
		List<Book> books=bookService.getAllBooks();
		model.addAttribute("books", books);
		// list.jsp + model = ModelAndView
        return "bookList";// WEB-INF/jsp/"list".jsp
	}
	
	
	@RequestMapping(value="/addBook",method={RequestMethod.GET,RequestMethod.POST})
	private String toAddBook(){
		return "addBook";
	}
	
	
	@RequestMapping(value="/ajax/addBook.do",method=RequestMethod.GET)
	@ResponseBody
	private Result<String> addBook(String name,int number){
		Integer insert=bookService.addBook(name, number);
		Result<String> result=new Result<>();
		logger.info(result.toString());
		if (insert==1) {
			logger.info("添加图书成功！");
			result.setData("book/list");
			result.setSuccess(true);
		}else{
			result.setSuccess(false);
			result.setError("服务器异常！");
		}
		return result;
		
		
	}

	// ajax json
    @RequestMapping(value = "/{bookId}/detail", method = RequestMethod.GET)
    @ResponseBody
    private String detail(@PathVariable("bookId") Long bookId, Model model) {
        if (bookId == null) {
            return "redirect:/book/list";
        }
        Book book = bookService.getBookById(bookId);
        if (book == null) {
            return "forward:/book/list";
        }
        model.addAttribute("book", book);
        return "detail";
    }

    @RequestMapping(value = "/ajax/{bookId}/appoint.do", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    private Result<AppointExcuetion> appoint(@PathVariable("bookId") Long bookId,HttpSession session) {
    	logger.info("exec appoint...");
    	UserInfo userInfo=(UserInfo) session.getAttribute("loginUser");
        if (userInfo==null) {
            return new Result<>(false, "请先完成登录操作！");
        }
        AppointExcuetion execution = bookService.appoint(bookId, new Long(userInfo.getUserId()));
        return new Result<AppointExcuetion>(true, execution);
    }
    
    @RequestMapping(value = "/ajax/{bookId}/del.do", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    private Result<Map<String, String>> del(@PathVariable("bookId") Long bookId,HttpSession session) {
    	logger.info("exec appoint...");
    	UserInfo userInfo=(UserInfo) session.getAttribute("loginUser");
        if (userInfo==null) {
            return new Result<>(false, "请先完成登录操作！");
        }
        Integer del = bookService.delBook(bookId);
        Map<String, String> result=new HashMap<>();
        if (del==1) {
        	result.put("states", "删除成功！");
        	 return new Result<Map<String,String>>(true, result);
		}else{
			result.put("states", "删除失败！");
			 return new Result<Map<String,String>>(false, result);
		}
       
       
    }
}
