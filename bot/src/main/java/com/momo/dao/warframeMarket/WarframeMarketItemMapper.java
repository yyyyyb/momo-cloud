package com.momo.dao.warframeMarket;

import com.momo.entity.warframeMarket.WarframeMarketItem;
import com.momo.entity.warframeMarket.WarframeRemark;
import com.momo.mapper.SuperMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarframeMarketItemMapper extends SuperMapper<WarframeMarketItem> {
    void deleteAllData1();
    void deleteAllData2();
    void deleteAllData3();
    void deleteAllData4();
    void deleteAllData5();

    void deleteRelic();

    void deletePrime();

    void deleteMod();

    @Select("select * from b_warframe_market_all_item where item_name in (select mod_name from b_warframe_wiki_mod)")
    List<WarframeMarketItem> getModItem();

    @Select("select * from b_warframe_remark")
    List<WarframeRemark> getAllWarframeRemark();

    List<WarframeMarketItem> getWarframeMarketUrl(String query);
}
