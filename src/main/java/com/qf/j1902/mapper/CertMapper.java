package com.qf.j1902.mapper;

import com.qf.j1902.pojo.Cert;
import com.qf.j1902.utils.Data;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Mapper
@Component
public interface CertMapper {
    Cert queryCert(Map<String, Object> paramMap);

    List<Cert> pageQuery(Map<String, Object> paramMap);

    int queryCount(Map<String, Object> paramMap);

    void insertCert(Cert cert);

    Cert queryById(Integer id);

    int updateCert(Cert cert);

    int deleteCert(Integer id);

    int deleteCerts(Data ds);

    List<Cert> queryCertByAccttype(String accttype);

    List<Cert> queryAllCert();

    List<Map<String, Object>> queryAllAccttypeCert();

    int insertAccttypeCert(Map<String, Object> map);

    int deleteAccttypeCert(Map<String, Object> map);

//    void insertMemberCert(MemberCert memberCert);
}
