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
        return sqlSession.insert(NAMESPACE+"insert",dto);
    }

    public int findTotalCount(PagingSearchAndSort pagingSearchAndSort){
        return sqlSession.selectOne(NAMESPACE+"selectCount", pagingSearchAndSort);
    }

    public List<ItemDto> findAllItems(PagingSearchAndSort pagingSearchAndSort) {
        return sqlSession.selectList(NAMESPACE+"selectAll", pagingSearchAndSort);
    }

    public List<ItemDto> findAllDisplayedItems(PagingSearchAndSort pagingSearchAndSort) {
    return sqlSession.selectList(NAMESPACE+"selectAllDisplayedItems", pagingSearchAndSort);
    }

    public ItemDto findItem(int itemSn) {
        return sqlSession.selectOne(NAMESPACE+"selectItem",itemSn);
    }

    public int updateItem(ItemDto dto) {
        return sqlSession.update(NAMESPACE+"updateItem",dto);
    }

    public int deleteItem(int itemSn){
        return sqlSession.delete(NAMESPACE+"deleteItem",itemSn);
    }

    public int updateStocks(List<ItemDto> itemDtos) {
        return sqlSession.update(NAMESPACE+"updateStocks",itemDtos);
    }
}
