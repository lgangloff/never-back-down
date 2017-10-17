package org.lgangloff.nbd.repository;

import java.util.List;

import org.lgangloff.nbd.domain.i18n.I18nKey;
import org.lgangloff.nbd.domain.i18n.I18nValue;
import org.lgangloff.nbd.domain.i18n.enumeration.LangKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA repository for the I18nValue entity.
 */
public interface I18nRepository extends JpaRepository<I18nValue,Long> {

    @Query("select val from I18nValue val "
    		+ "left join fetch val.i18nKey key "
    		+ "where key.groupName = :groupName and val.langKey = :langKey")
	List<I18nValue> findAllByGroupName(@Param("groupName") String groupName, @Param("langKey") LangKey langKey);

    @Query("select val from I18nValue val "
    		+ "left join fetch val.i18nKey key "
    		+ "where key.groupName = :groupName")
    List<I18nValue> findAllByGroupName(@Param("groupName") String groupName);

    @Query("select key from I18nKey key "
    		+ "where key.groupName = :groupName and  key.name = :name")
	I18nKey findI18nKey(@Param("name") String name, @Param("groupName") String groupName);
    

}
