package org.lzmhc.dto.factory;

import org.lzmhc.dto.ItemInterface.InfoItem;

public class InfoFactory {
    public static <T extends InfoItem> T createDto(Class<T> dto) {
        InfoItem infoItem = null;
        try{
            infoItem = (InfoItem) Class.forName(dto.getName()).newInstance();
        }catch (Exception e){

        }
        return (T) infoItem;
    }
}
