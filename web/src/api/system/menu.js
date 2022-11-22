import request from '@/utils/request'

/*
菜单管理相关的API请求函数
*/
const api_name = '/menu'

export default {

    /*
    获取权限(菜单/功能)列表
    */
    findNodes() {
        return request({
            url: `${api_name}/tree`,
            method: 'get'
        })
    },

    /*
    删除
    */
    removeById(id) {
        return request({
            url: `${api_name}/delete/${id}`,
            method: "delete"
        })
    },

    /*
    保存 添加
    */
    save(sysMenu) {
        return request({
            url: `${api_name}/create`,
            method: "post",
            data: sysMenu
        })
    },

    /*
    更新
    */
    updateById(sysMenu) {
        return request({
            url: `${api_name}/update`,
            method: "put",
            data: sysMenu
        })
    },

    /*
    查看某个角色的权限列表
    */
    toAssign(roleId) {
        return request({
            url: `${api_name}/toAssign/${roleId}`,
            method: 'get'
        })
    },

    /*
    给某个角色授权
    */
    doAssign(assginMenuVo) {
        return request({
            url: `${api_name}/doAssign`,
            method: "post",
            data: assginMenuVo
        })
    }
}
