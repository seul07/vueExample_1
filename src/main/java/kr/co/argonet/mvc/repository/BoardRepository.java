package kr.co.argonet.mvc.repository;


import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.argonet.mvc.domain.Board;
import kr.co.argonet.mvc.domain.Paging;
/**
 * 게시판 Repository 
 * @author Yeseul Jo
 */
@Repository
public interface BoardRepository {
	
	List<Board> getList();
	
	Board get(int boardSeq);
	
	void save(Board board);
	
	void update(Board board);
	
	void delete(int boardSeq);

	List<Board> getList(Paging<Board> pagingVO);

	int getCount(Paging<Board> pagingVO);
	
	
}
