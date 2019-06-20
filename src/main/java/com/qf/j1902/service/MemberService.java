package com.qf.j1902.service;

import com.qf.j1902.pojo.Member;

import java.util.List;
import java.util.Map;

public interface MemberService {
    Member queryMebmerlogin(Map<String, Object> paramMap);

    void updateAcctType(Member loginMember);

    void updateBasicinfo(Member loginMember);

    void updateEmail(Member loginMember);

    void updateAuthstatus(Member loginMember);

    Member getMemberById(Integer memberid);

    List<Map<String, Object>> queryCertByMemberid(Integer memberid);

    Member selectMemberByLoginacct(String loginacct);
}
