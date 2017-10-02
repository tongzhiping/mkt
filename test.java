package com.csii.ifp.mkt.tools.action;

import java.util.Map;
import org.springframework.util.CollectionUtils;
import com.csii.ifp.ibs.action.IbsQueryAction;
import com.csii.mkt.dao.MktToolsDao;
import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;


/**
 * Description: 根据用户端传输的couponseq查询卡券详情（只查：未删除&&未使用&&在有效时间内的）
 * Copyright (c) CSII.
 * All Rights Reserved.
 * 
 * @version 1.0 2017年7月12日 上午11:33:35 by tongzhiping (tongzhiping@csii.com.cn)
 */
public class MktCheckUserCouponDetailAction extends IbsQueryAction {

    private MktToolsDao mktToolsDao;

    @Override
    public void execute(Context context) throws PeException {
        context.setData("NowTime", context.getTimestamp());
        Map<String, Object> map = mktToolsDao.qryUserCouponDetail(context.getDataMap());
        if (CollectionUtils.isEmpty(map)) {
            context.setData("ReturnCode", "999999");
            context.setData("ReturnMsg", "卡券不存在");
        } else {
            context.setData("ReturnCode", "000000");
            context.setData("ReturnMsg", "查询成功");
            context.setData("Map", map);
        }
    }

    public MktToolsDao getMktToolsDao() {
        return mktToolsDao;
    }

    public void setMktToolsDao(MktToolsDao mktToolsDao) {
        this.mktToolsDao = mktToolsDao;
    }
}
