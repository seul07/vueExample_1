package kr.co.argonet.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.argonet.mvc.domain.Member;
import kr.co.argonet.mvc.jwt.JwtService;
import kr.co.argonet.mvc.service.MemberService;

@RestController
@RequestMapping("/member")
public class LoginController {
	Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
    private JwtService jwtService;

    @Autowired
    private MemberService memberService;

    @PostMapping("/login")	
    public JSONObject login(@RequestBody Member member,
            HttpServletResponse res) {
        Map<String, Object> resultMap = new HashMap<>();
        JSONObject json = new JSONObject();
        HttpStatus status = null;
        String token = "";
        try {
            Member loginUser = memberService.signin(member);
            // 로그인 성공했다면 토큰을 생성
            token = jwtService.create(loginUser);
            res.setHeader("jwt-auth-token", token);

            resultMap.put("status", true);
            resultMap.put("data", loginUser);
            status = HttpStatus.ACCEPTED;
        } catch (RuntimeException e) {
            log.error("로그인 실패", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        
        json.put("resultMap", resultMap);
        json.put("status", status);
        json.put("token", token);
        
        return json;
    }
    
    @PostMapping("/info")
    public JSONObject getInfo(HttpServletRequest req,
            @RequestBody Member member) {
        Map<String, Object> resultMap = new HashMap<>();
        JSONObject json = new JSONObject();
        HttpStatus status = null;
        try {
            // 사용자에게 전달할 정보
            String info = memberService.getServerInfo();
            resultMap.putAll(jwtService.get(req.getHeader("jwt-auth-token")));

            resultMap.put("status", true);
            resultMap.put("info", info);
            resultMap.put("request_body", member);
            status = HttpStatus.ACCEPTED;
        } catch (RuntimeException e) {
            log.error("정보조회 실패", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        json.put("resultMap", resultMap);
        json.put("status", status);
        
        return json;
    }
}
