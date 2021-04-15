package kr.co.argonet.mvc.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.argonet.mvc.domain.Board;
import kr.co.argonet.mvc.domain.Paging;
import kr.co.argonet.mvc.repository.BoardRepository;
/**
 * 게시판 서비스
 * @author Yeseul Jo
 */
@Service
public class BoardService {
	
	@Autowired
	private BoardRepository repository;
	/**
	 * 
	 * @return
	 */
	public List<Board> getList(){
		return repository.getList();
	}
	/**
	 * 
	 * @param boardSeq
	 * @return
	 */
	public Board get(int boardSeq) {
		return repository.get(boardSeq);
	}
	/**
	 * 
	 * @param board
	 */
	public void save(Board board) {
		repository.save(board);
	}
	/**
	 * 
	 * @param board
	 */
	public void update(Board board) {
		repository.update(board);
	}
	/**
	 * 
	 * @param board
	 */
	public void delete(int boardSeq) {
		repository.delete(boardSeq);
	}
	
	public List<Board> getList(Paging<Board> pagingVO) {
		return repository.getList(pagingVO);
	}
	public int retrieveBoardCount(Paging<Board> pagingVO) {
		return repository.getCount(pagingVO);
	}
	
	
}
