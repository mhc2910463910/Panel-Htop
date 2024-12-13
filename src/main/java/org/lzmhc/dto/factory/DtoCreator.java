package org.lzmhc.dto.factory;

import org.lzmhc.dto.ItemInterface.InfoItem;

/**
 * 工厂模式
 */
public abstract class DtoCreator {
    public abstract <T extends InfoItem> T createDto(Class<T> dto);
}
