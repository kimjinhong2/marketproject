package com.project.market.global.config.security.jwt;

import com.project.market.domain.member.constant.MemberRole;
import com.project.market.domain.member.entity.Member;
import com.project.market.global.config.security.UserDetailsServiceImpl;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

	@Value("${token.access-token-expriation-time:900000}")//���ѽð�
	private String accessTokenExpirationTime;
	
	@Value("${token.refresh-token-expiration-time:1210500000")
	private String refreshTokenExpirationTime;
	
	@Value("${token.secret}")
	private String tokenSecret;
	
	private final UserDetailsServiceImpl userDetailsService;
	
	public TokenDto createTokenDto(Member member) {
		return TokenDto.builder()
				.accessToken(createAccessToken(member.getEmail(), member.getRole(), createAccessTokenExpireTime()))
				.refreshToken(createRefreshToken(member.getEmail(), createRefreshTokenExpireTime()))
				.build();
	}
	
	public String createAccessToken(String email, MemberRole role, Date expirationTime) {
        String accessToken = Jwts.builder()
                .setSubject(TokenType.ACCESS.name())                // ��ū ����
                .setAudience(email)                                 // ��ū �����
                .setIssuedAt(new Date())                            // ��ū �߱� �ð�
                .setExpiration(expirationTime)                      // ��ū ���� �ð�
                .claim("role", role)                          // ���� role
                .signWith(SignatureAlgorithm.HS512, tokenSecret)
                .setHeaderParam("typ", "JWT")
                .compact();
        return accessToken;
    }

    public String createRefreshToken(String email, Date expirationTime) {
        String refreshToken = Jwts.builder()
                .setSubject(TokenType.REFRESH.name())               // ��ū ����
                .setAudience(email)                                 // ��ū �����
                .setIssuedAt(new Date())                            // ��ū �߱� �ð�
                .setExpiration(expirationTime)                      // ��ū ���� �ð�
                .signWith(SignatureAlgorithm.HS512, tokenSecret)
                .setHeaderParam("typ", "JWT")
                .compact();
        return refreshToken;
    }

    public Date createAccessTokenExpireTime() {//�׼��� ��ū ���� �ð� ����
        return new Date(System.currentTimeMillis() + Long.parseLong(accessTokenExpirationTime));
    }

    public Date createRefreshTokenExpireTime() {//�������� ��ū ���� �ð� ����
        return new Date(System.currentTimeMillis() + Long.parseLong(refreshTokenExpirationTime));
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getEmail(String token) {
        return Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody().getAudience();//���ڵ� �Ǿ��� ��ū�� ���ڵ�
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        } 
    }

    

    public boolean isTokenExpired(String token) {
    	Date expiration = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody().getExpiration();
    	Date now = new Date();
        if(now.after(expiration)) {
        	return true;
        }
        return false;
    }
}
