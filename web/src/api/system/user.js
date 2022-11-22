import request from '@/utils/request'

//常量
const api_name = 'user'

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
    //添加
    save(user) {
        return request({
            //接口路径
            url: `${api_name}/create`,
            method: 'post', //提交方式
            //参数
            data: user
        })
    },
    //根据id查询
    getUserId(id) {
        return request({
            //接口路径
            url: `${api_name}/detail/${id}`,
            method: 'get' //提交方式
        })
    },
    //修改
    update(user) {
        return request({
            //接口路径
            url: `${api_name}/update`,
            method: 'put', //提交方式
            //参数
            data: user
        })
    },
    //删除
    removeById(id) {
        return request({
            //接口路径
            url: `${api_name}/delete/${id}`,
            method: 'delete' //提交方式
        })
    },
    //更改用户状态
    updateStatus(id, enable) {
        return request({
            //接口路径
            url: `${api_name}/status/${id}/${enable}`,
            method: 'put' //提交方式
        })
    },
}
