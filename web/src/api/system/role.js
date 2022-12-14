import request from '@/utils/request'

//常量
const api_name = 'role'

export default {
    //列表
    getPageList(page, limit, searchObj) {
        return request({
            //接口路径
            url: `${api_name}/page?pageNum=${page}&pageSize=${limit}`,
            method: 'get', //提交方式
            //参数
            params: searchObj
        })
    },
    //删除
    removeId(id) {
        return request({
            //接口路径
            url: `${api_name}/delete/${id}`,
            method: 'delete' //提交方式
        })
    },
    //添加
    saveRole(role) {
        return request({
            //接口路径
            url: `${api_name}/create`,
            method: 'post', //提交方式
            //传递json格式
            data: role
        })
    },
    //根据id查询
    getRoleId(id) {
        return request({
            //接口路径
            url: `${api_name}/detail/${id}`,
            method: 'get' //提交方式
        })
    },
    //修改的方法
    update(role) {
        return request({
            //接口路径
            url: `${api_name}/update`,
            method: 'put', //提交方式
            data: role
        })
    },
    //批量删除
    batchRemove(idList) {
        return request({
            //接口路径
            url: `${api_name}/delete`,
            method: 'delete', //提交方式
            data: idList
        })
    }
}
