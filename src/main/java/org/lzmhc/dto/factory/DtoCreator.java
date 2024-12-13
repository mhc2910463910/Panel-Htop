package org.lzmhc.dto.factory;

import org.lzmhc.dto.ItemInterface.InfoItem;

public abstract class DtoCreator {
    public abstract <T extends InfoItem> T createDto(Class<T> dto);
}
