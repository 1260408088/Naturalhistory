//package com.king.naturalhistory.dao;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Map;
//
//public interface PoetriesRepository extends JpaRepository<Map, Integer> {
//    List<Map> findByTitle(String title);
//
//    @Query(value = "select poets.name,poetries.title,poetries.content,poetries.created_at,poetries.updated_at"
//            + " from poets LEFT JOIN poetries ON poetries.poet_id = poets.id where if(:anther!='',poets.name=:anther,1=1) AND if(:title!='',poetries.title=:title,1=1) AND "+ "  if(:content!='',poetries.content like CONCAT('%',:content,'%'),1=1) AND" +
//            " if(:beginDate!=''and :endDate!='',date_format(poetries.created_at,'%Y-%m-%d') between :beginDate and :endDate,1=1 ) order by poetries.created_at ASC  limit :start,:pageSize ", nativeQuery = true)
//    List<Map<String,Object>> findAllByParam(@Param("beginDate") String beginDate, @Param("endDate") String endDate,@Param("content") String content,
//                                            @Param("anther") String anther, @Param("title") String title,@Param("start") int start,@Param("pageSize") int pageSize);
//}
