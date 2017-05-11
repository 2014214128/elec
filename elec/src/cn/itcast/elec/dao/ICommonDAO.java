package cn.itcast.elec.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import cn.itcast.elec.domain.ElecSystemDDL;
import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.domain.ElecUser;

public interface ICommonDAO<T> {
	/**
	 * @Name: save
	 * @Description: 增加对象的方法
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: T entity 对象
	 * @Return: 无
	 */
	public void save(T t);
	/**
	 * @Name: update
	 * @Description: 修改对象的方法
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: T entity 对象
	 * @Return: 无
	 */
	public void update(T t);
	/**
	 * @Name: findObjectById
	 * @Description: 使用主键ID查询对象
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: Serializable id 主键ID
	 * @Return: T
	 */
	public T findObjectById(Serializable id);
	/**
	 * @Name: deleteObjectByIds
	 * @Description: 使用主键IDs删除对象
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: Serializable[] ids 主键ID数组
	 * @Return: 无
	 */
	public void deleteObjectByIds(Serializable...ids);
	/**
	 * @Name: deleteObjectByCollection
	 * @Description: 使用集合对象进行删除
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: List<T> list list集合
	 * @Return: 无
	 */
	void deleteObjectByCollection(Collection<T> list);
	/**
	 * @Name: findCollectionByConditionNoPage
	 * @Description: 无分页使用查询条件查询ElecText对象的方法
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: String hqlWhere,Object[] params, LinkedHashMap<String, String> orderBy
	 * @Return: List<ElecText>
	 */
	List<T> findCollectionByConditionNoPage(String hqlWhere,
			Object[] params, LinkedHashMap<String, String> orderBy);
	/**
	 * @Name: saveObjectByCollection
	 * @Description: 增加对象集合的方法
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: Collection<T> entities
	 * @Return: 无
	 */
	public void saveObjectByCollection(Collection<T> list);
	
	T findObjectByID(String userID);
}
