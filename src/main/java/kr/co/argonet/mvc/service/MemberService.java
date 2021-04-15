package kr.co.argonet.mvc.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.argonet.mvc.domain.Member;
import kr.co.argonet.mvc.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	MemberRepository memberRepository;

//	@Override
//	@Transactional
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepository.findByUsername(username)
//				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
//
//		return UserDetailsImpl.build(user);
//	}
	
	// 로그인 계정은 고정값을 이용한다.
    public Member signin(Member member) {
//    	member.setMemId(memId);
//    	member.setMemId(memPass);
    	Member selectmember  = new Member();
    	selectmember = memberRepository.selectMember(member);
        if (member.getMemId().equals(selectmember.getMemId()) && member.getMemPass().equals(selectmember.getMemPass())) {
            return new Member(selectmember.getMemId(),selectmember.getMemPass(),selectmember.getMemName());
        } else {
            throw new RuntimeException("로그인 정보 없음");
        }
    }

    // 귀중한 정보를 전달한다.
    public String getServerInfo() {
        return "made by Joy";
    }
	
}
