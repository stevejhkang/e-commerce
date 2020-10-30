package com.example.shop.dao.item;

import com.example.shop.util.PagingSearchAndSort;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemDao {
    @Autowired
    private SqlSession sqlSession;

    protected static final String NAMESPACE = "com.example.shop.ItemMapper.";

    public int createItem(ItemDto dto){
        return sqlSession.insert(NAMESPACE+"createItem",dto);
    }

    public int findTotalCount(PagingSearchAndSort pagingSearchAndSort){
        return sqlSession.selectOne(NAMESPACE+"findTotalCount", pagingSearchAndSort);
    }

    public List<ItemDto> findAllItems(PagingSearchAndSort pagingSearchAndSort) {
        return sqlSession.selectList(NAMESPACE+"findAllItems", pagingSearchAndSort);
    }

    public List<ItemDto> findAllDisplayedItems(PagingSearchAndSort pagingSearchAndSort) {
    return sqlSession.selectList(NAMESPACE+"findAllDisplayedItems", pagingSearchAndSort);
    }

    public ItemDto findItemByItemSn(int itemSn) {
        return sqlSession.selectOne(NAMESPACE+"findItemByItemSn",itemSn);
    }

    public int updateItem(ItemDto dto) {
        return sqlSession.update(NAMESPACE+"updateItem",dto);
    }

    public int updateStocks(List<ItemDto> itemDtos) {
        return sqlSession.update(NAMESPACE+"updateStocks",itemDtos);
    }

    public int deleteItem(int itemSn){
        return sqlSession.delete(NAMESPACE+"deleteItem",itemSn);
    }


}
