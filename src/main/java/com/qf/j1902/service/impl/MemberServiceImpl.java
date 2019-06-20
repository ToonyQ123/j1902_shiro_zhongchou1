package com.qf.j1902.service.impl;


import com.qf.j1902.mapper.MemberMapper;
import com.qf.j1902.pojo.Member;
import com.qf.j1902.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;
    @Override
    public Member queryMebmerlogin(Map<String, Object> paramMap) {
        return memberMapper.queryMebmerlogin(paramMap);
    }

    @Override
    public void updateAcctType(Member loginMember) {
        memberMapper.updateAcctType(loginMember);
    }

    @Override
    public void updateBasicinfo(Member loginMember) {

    }

    @Override
    public void updateEmail(Member loginMember) {

    }

    @Override
    public void updateAuthstatus(Member loginMember) {

    }

    @Override
    public Member getMemberById(Integer memberid) {
        return null;
    }

    @Override
    public List<Map<String, Object>> queryCertByMemberid(Integer memberid) {
        return null;
    }

    @Override
    public Member selectMemberByLoginacct(String loginacct) {
        return memberMapper.findMemberByLoginacct(loginacct);
    }
}
