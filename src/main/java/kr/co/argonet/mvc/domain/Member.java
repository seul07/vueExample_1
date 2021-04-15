package kr.co.argonet.mvc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private String memId;
	private String memPass;
	private String memName;
	
}
