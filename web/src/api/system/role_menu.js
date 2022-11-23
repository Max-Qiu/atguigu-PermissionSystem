import request from '@/utils/request'

/*
菜单管理相关的API请求函数
*/
const api_name = '/role-menu'

export default {
    /*
    查看某个角色的权限列表
    */
    toAssign(roleId) {
        return request({
            url: `${api_name}/get/${roleId}`,
            method: 'get'
        })
    },

    /*
    给某个角色授权
    */
    doAssign(assginMenuVo) {
        return request({
            url: `${api_name}/post`,
            method: "post",
            data: assginMenuVo
        })
    }
}
