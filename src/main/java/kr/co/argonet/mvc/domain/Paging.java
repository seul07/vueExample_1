package kr.co.argonet.mvc.domain;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Paging<T> {
	public Paging(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}

	private int totalRecord;
	private int screenSize = 10;
	private int blockSize = 5;
	private int currentPage;
	
	private int totalPage;
	private int startRow;
	private int endRow; 
	private int startPage;
	private int endPage;
	private List<T> dataList;  //페이지로넘어갈때 멤버테이터도포함됨
	
	private Search searchVO;
	private T searchDetail;
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		this.totalPage = (totalRecord+(screenSize - 1))/screenSize;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		this.endRow = currentPage*screenSize;
		this.startRow = endRow - (screenSize - 1);
		this.startPage = blockSize * ( (currentPage-1) / blockSize) + 1 ;
		this.endPage = startPage + (blockSize - 1);
	}
	
	private static final String LIPTRN = "<li class='page-item %s' %s>";
	private static final String APTRN = "<a class='page-link' href='#' data-page='%s'>%s</a>";
	private static final String SPANPTRN = "<span class='page-link'>%s</span>";
	
	public String getPagingHTML() {
		StringBuffer html = new StringBuffer();
		html.append("<ul class='pagination'>");
		// previous
		html.append(String.format(LIPTRN, startPage < blockSize?"disabled":"", ""));
		if(startPage < blockSize) {
			html.append(String.format(SPANPTRN, "Previous"));
		}else {
			html.append(String.format(APTRN, (startPage - blockSize), "Previous"));
		}
		html.append("</li>");
		
		// page number
		if(endPage>totalPage) endPage = totalPage;
		for(int page=startPage; page<=endPage; page++) {
			html.append(String.format(LIPTRN, page==currentPage?"active":"", 
											page==currentPage?"aria-current='page'":""));
			if(page==currentPage) {
				html.append(String.format(SPANPTRN, page));
			}else {
				html.append(String.format(APTRN, page, page));
			}
			html.append("</li>");
		}
		
		// Next
		html.append(String.format(LIPTRN, endPage >= totalPage?"disabled":"", ""));
		if(endPage >= totalPage) {
			html.append(String.format(SPANPTRN, "Next"));
		}else {
			html.append(String.format(APTRN, (endPage + 1) ,"Next"));
		}
		html.append("</li>");
		html.append("</ul>");
		return html.toString();
	}
	
}
