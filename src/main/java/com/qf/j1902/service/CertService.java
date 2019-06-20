package com.qf.j1902.service;

import com.qf.j1902.pojo.Cert;
import com.qf.j1902.utils.Data;
import com.qf.j1902.utils.Page;

import java.util.List;
import java.util.Map;

public interface CertService {
    public Cert queryCert(Map<String, Object> paramMap);

    public Page<Cert> pageQuery(Map<String, Object> paramMap);

    public int queryCount(Map<String, Object> paramMap);

    public void insertCert(Cert cert);

    public Cert queryById(Integer id);

    public int updateCert(Cert cert);

    public int deleteCert(Integer id);

    public int deleteCerts(Data ds);

    public List<Cert> queryCertByAccttype(String accttype);

    public List<Cert> queryAllCert();

    public List<Map<String, Object>> queryAllAccttypeCert();

    public int insertAccttypeCert(Map<String, Object> map);

    public int deleteAccttypeCert(Map<String, Object> map);

//    public void saveMemberCert(List<MemberCert> certimgs);
}
