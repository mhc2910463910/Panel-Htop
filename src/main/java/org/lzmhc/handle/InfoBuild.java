package org.lzmhc.handle;

import org.lzmhc.dto.ItemInterface.InfoItem;

import java.util.concurrent.CountDownLatch;

public interface InfoBuild {
    /**
     * 动态数据更新
     */
    public void build();

}
