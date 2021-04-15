package kr.co.argonet.mvc.controller;


import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.argonet.mvc.domain.Board;
import kr.co.argonet.mvc.domain.Paging;
import kr.co.argonet.mvc.domain.Search;
import kr.co.argonet.mvc.service.BoardService;
/**
 * 게시판 컨트롤러
 * @author Yeseul Jo
 */
@RestController
@RequestMapping("/board")
public class BoardController{
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired 
	BoardService boardService;
	//https://daily-life-of-bsh.tistory.com/208  // paging spring 
	//https://berrrrr.github.io/programming/2020/11/15/vue-b-pagination-page-click/     페이징 구현
	//https://blog.naver.com/PostView.nhn?blogId=gngh0101&logNo=221353345745&parentCategoryNo=&categoryNo=32&viewDate=&isShowPopularPosts=true&from=search      페이징
	//https://github.com/jonashackt/spring-boot-vuejs#secure-spring-boot-backend-and-protect-vuejs-frontend     로그인
	//https://bezkoder.com/spring-boot-vue-js-authentication-jwt-spring-security/       로그인
	//https://parandol.tistory.com/13      로그인
	//https://goodteacher.tistory.com/97   로그인
	// json
	// restFull
	// paging
	// bootstrap 
	// router
	// interceptor
	// rocale
	// message Property
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/list")
	public JSONObject getList(@RequestParam(value="page",required=false,defaultValue = "1")int currentPage,Search search) {
		Paging<Board> pagingVO = new Paging<>(5,5);
		pagingVO.setSearchVO(search);
		
		int totalRecord = boardService.retrieveBoardCount(pagingVO);
		
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		List<Board> list = boardService.getList(pagingVO);
		pagingVO.setDataList(list);
		JSONObject json = new JSONObject();
		json.put("pagingVO", pagingVO);
		return json;
	}
//	/**
//	 * 
//	 * @return
//	 */
//	@GetMapping("/list")
//	public Map<String, List<Board>> getList() {
//		logger.info("getList");
//		System.out.println("리스트");
//		List<Board> list = boardService.getList();
//		Map<String, List<Board>> map = new HashMap<String, List<Board>>();
//		map.put("list", list);
//		return map;
//	}

	/**
	 * 
	 * @param boardSeq
	 * @return
	 */
	@GetMapping("/view/{boardSeq}")
	public JSONObject get(@PathVariable int boardSeq) {
		JSONObject json = new JSONObject();
		Board board = boardService.get(boardSeq);
		json.put("board", board);
		return json;
	}
	/**
	 * 등록/수정처리
	 * @param board
	 */
	@PostMapping("/save")
	@ResponseBody
	public JSONObject save(@RequestBody Board board ) {
		JSONObject json = new JSONObject();
		String msg = "실패";
		try {
			boardService.save(board);
			msg = "성공";
		} catch (Exception e) {
			
		}
		json.put("message", msg);
		return json;
	}
//	/**
//	 * 등록/수정처리
//	 * @param board
//	 */
//	@PostMapping("/save")
//	@ResponseBody
//	public Map<String, String> save(@RequestBody Board board ) {
//		Map<String, String> map = new HashMap<String, String>();
//		String msg = "실패";
//		try {
//			boardService.save(board);
//			msg = "성공";
//		} catch (Exception e) {
//			
//		}
//		map.put("message", msg);
//		return map;
//	}
	/**
	 * 
	 * @param board
	 */
	@GetMapping("/delete/{boardSeq}")
	public void delete(@PathVariable int boardSeq) {
		boardService.delete(boardSeq);
	}
	
	
}
