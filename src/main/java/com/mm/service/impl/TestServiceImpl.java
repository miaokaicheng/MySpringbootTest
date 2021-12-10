package com.mm.service.impl;

import com.mm.mapper.mysql.TestMapper;
import com.mm.service.TestService;
import com.mm.util.RsaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: TestServiceImpl
 * @author: MKC
 * @date: 2021-11-25 17:43
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper mapper;

    @Override
    public List<Map<String, Object>> getList() {
        List<Map<String,Object>> list = mapper.getList();
        String type = "0";
       /* String code = "02";*/
        String code = "03";
        if ("04.1".equals(code))
            list.forEach(m -> {
                getSpecialResult("dddNum", type, m);
                getSpecialResult("dddUnit", type, m);
                getSpecialResult("ambClassify", type, m);
                getSpecialResult("ambLevel", type, m);
                getSpecialResult("phe", type, m);
                getSpecialResult("highRiskDrug", type, m);
                getSpecialResult("monitoredDrug", type, m);
                getSpecialResult("analeptic", type, m);
                getSpecialResult("atmDrug", type, m);
                getSpecialResult("atmDrugLevel", type, m);
                getSpecialResult("atmAdjuvantDrug", type, m);
                getSpecialResult("firstLevelCoding", type, m);
                getSpecialResult("pamc1LevelClassify", type, m);
                getSpecialResult("pamc2LevelClassify", type, m);
                getSpecialResult("pamc3LevelClassify", type, m);
                getSpecialResult("pamc4LevelClassify", type, m);
                getSpecialResult("sdCheck", type, m);
                getSpecialResult("fdaPreClassify", type, m);
                getSpecialResult("pamcClassify", type, m);
                getSpecialResult("drugCpt1", type, m);
                getSpecialResult("drugCpt2", type, m);
            });
        if ("02".equals(code))
            list.forEach(m -> {
                getSpecialResult("minInvasiveType", type, m);
                getSpecialResult("indication", type, m);
                getSpecialResult("contraindications", type, m);
                getSpecialResult("preoperative", type, m);
                getSpecialResult("anesthesia", type, m);
                getSpecialResult("surgicalProcedure", type, m);
                getSpecialResult("intraoperative", type, m);
                getSpecialResult("postoperative", type, m);
                getSpecialResult("complication", type, m);
                //更新数据库
                //mapper.update(m);
            });
        if ("05".equals(code))
            list.forEach(m -> {
                getSpecialResult("performanceComposition", type, m);
                getSpecialResult("productScope", type, m);
            });
        if ("03".equals(code))
            list.forEach(m -> {
                getSpecialResult("icd9operName", type, m);
                getSpecialResult("icd9operCode", type, m);
                getSpecialResult("checkItem", type, m);
                getSpecialResult("diagCode", type, m);
                getSpecialResult("medName", type, m);
                getSpecialResult("medInstCode", type, m);
                getSpecialResult("medSixCode", type, m);
                //mapper.update(m);
            });
        if ("06".equals(code))
            list.forEach(m -> {
                getSpecialResult("suitableObject", type, m);
                getSpecialResult("treatMethod", type, m);
                getSpecialResult("latestGuide", type, m);
            });
        return list;
    }

    @Override
    public List<Map<String, Object>> getList2() {
        List<Map<String,Object>> list = mapper.getList2().stream().distinct().collect(Collectors.toList());
        BigDecimal hifPay = new BigDecimal(0);

        for(Map<String,Object> map : list){
            List<BigDecimal> amtList = mapper.getList3(map);
            for(BigDecimal amt : amtList){
                hifPay = hifPay.add(amt);
            }
            map.put("amt",hifPay);
            mapper.update2(map);
            hifPay = new BigDecimal(0);
        }
        return list;
    }

  /*  @Override
    public List<Map<String, Object>> getBasicList() {
        return getList();
    }*/

    public void getSpecialResult(String key, String type, Map<String, Object> m) {
        if (type.equals("1") && m.containsKey(key)) {
            String str = (String)m.get(key);
            try {
                m.put(key, RsaUtil.splitDecrypt(str, "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIrINfsVQAfQR/XSb6cxB29k4fujITcLt/j0NSH7R8a5i2nZcPPK/Qv5olfxgmsaOIsf0NkRuZqO/V4xhTVoTmTrKf9B6pWpR1IttaKS3yWoNgp9Os0uKctOKk+ceoM16uoztLW2Q6bkbfhtHv1ty89WwKt//NUfRRnEOxv1Mx+nAgMBAAECgYEAic023HNDOv7zFQG43/hs+4zeXvCh4HiAWnCR+YX6xTYjWh0QbOKX1LYxHsWtzA8jKfGTfJZ/BxsINelR7a/+dfp/gl8lAQz92ZCcpWeRS4kGoD3YFMWPAwGl+XE9biYWVYumUefjCqZMDbbIX31kv11k8hGmdHFtTUCW+oj9ISECQQDPqUEk+o6NjUdTnSobVe4UvHMQ3r0vwoedqqVHg+oBh8GUS8Rw8f2isgg/2DXXMkcvLqmALiGKwpNxoNMJhn6fAkEAqxZhSsVd7Ge9xIVS5YUA3myw39Icay/NB7IMKk6+5OM0EhYIFknWmjrxXpexVq3wOT2WLqKwDkSDvApWA1Wp+QJAOfafdWfupHo7343t8+VfaDBV2e6iVhNxcUPxkG20wqqXEQK5GWGij2gsP03lcWTaU8QtkdbOjAHV0BC4916wNwJACSpmxfGy6XJZWUjnOwKYHFJoW2VPPnLOIiZovm9/jJWbeYiSoFcOVy7nNXEdAA7LetWQ0SjIE8uZ3x4So5UYSQJATtQz89kOEP9rjO6URpLtfrb8YNge9Aab76+rq/EgjZ7RG1sgAHJDpZc8lu7aIKLVVsDo8/sxtmFn4MFWAA495w=="));
            } catch (Exception e) {
                throw new RuntimeException(str + "解密失败");
            }
        } else if (type.equals("0") && m.containsKey(key)) {
            String str = (String)m.get(key);
            try {
                m.put(key, RsaUtil.splitEncrypt(str, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCKyDX7FUAH0Ef10m+nMQdvZOH7oyE3C7f49DUh+0fGuYtp2XDzyv0L+aJX8YJrGjiLH9DZEbmajv1eMYU1aE5k6yn/QeqVqUdSLbWikt8lqDYKfTrNLinLTipPnHqDNerqM7S1tkOm5G34bR79bcvPVsCrf/zVH0UZxDsb9TMfpwIDAQAB"));
            } catch (Exception e) {
                throw new RuntimeException(str + "加密失败");
            }
        }
    }
}
