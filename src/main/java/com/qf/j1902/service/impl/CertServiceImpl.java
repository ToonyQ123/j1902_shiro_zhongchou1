package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.CertMapper;
import com.qf.j1902.pojo.Cert;
import com.qf.j1902.service.CertService;
import com.qf.j1902.utils.Data;
import com.qf.j1902.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class CertServiceImpl implements CertService {
    @Autowired
    private CertMapper certMapper;

    public Cert queryCert(Map<String, Object> paramMap) {
        return certMapper.queryCert(paramMap);
    }

    public Page<Cert> pageQuery(Map<String, Object> paramMap) {
        Page<Cert> certPage = new Page<Cert>((Integer)paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));
        paramMap.put("startIndex", certPage.getStartIndex());
        List<Cert> certs = certMapper.pageQuery(paramMap);
        // 获取数据的总条数
        int count = certMapper.queryCount(paramMap);
        certPage.setData(certs);
        certPage.setTotalsize(count);
        return certPage;
    }

    public int queryCount(Map<String, Object> paramMap) {
        return certMapper.queryCount(paramMap);
    }

    public void insertCert(Cert cert) {
        certMapper.insertCert(cert);
    }

    public Cert queryById(Integer id) {
        return certMapper.queryById(id);
    }

    public int updateCert(Cert cert) {
        return certMapper.updateCert(cert);
    }

    public int deleteCert(Integer id) {
        return certMapper.deleteCert(id);
    }

    public int deleteCerts(Data ds) {
        return certMapper.deleteCerts(ds);
    }

    public List<Cert> queryCertByAccttype(String accttype) {
        return certMapper.queryCertByAccttype(accttype);
    }

    @Override
    public List<Cert> queryAllCert() {
        return certMapper.queryAllCert();
    }

    @Override
    public List<Map<String, Object>> queryAllAccttypeCert() {
        return certMapper.queryAllAccttypeCert();
    }

    @Override
    public int insertAccttypeCert(Map<String, Object> map) {
        return certMapper.insertAccttypeCert(map);
    }

    @Override
    public int deleteAccttypeCert(Map<String, Object> map) {
        return certMapper.deleteAccttypeCert(map);
    }

/*    @Override
    public void saveMemberCert(List<MemberCert> certimgs) {
        for (MemberCert memberCert : certimgs) {
            certDao.insertMemberCert(memberCert);
        }
    }*/
}
