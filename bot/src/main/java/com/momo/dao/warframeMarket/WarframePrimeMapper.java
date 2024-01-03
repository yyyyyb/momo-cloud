package com.momo.dao.warframeMarket;

import com.momo.entity.warframeMarket.WarframePrime;
import com.momo.entity.warframeMarket.WarframeRelic;
import com.momo.mapper.SuperMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarframePrimeMapper extends SuperMapper<WarframePrime> {

    @Select("select * from b_warframe_prime")
    List<WarframePrime> getAllPrimeSet();
}
