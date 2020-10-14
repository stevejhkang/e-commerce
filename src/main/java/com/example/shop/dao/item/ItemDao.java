package com.example.shop.dao.item;

import com.example.shop.util.Paging;
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

    public int findTotalCount(){
        return sqlSession.selectOne(NAMESPACE+"selectCount");
    }
    public List<ItemDto> findAll(Paging paging) {
        return sqlSession.selectList(NAMESPACE+"selectAll",paging);
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
}
