import request from '@/utils/request'

//常量
const api_name = '/user-role'

export default {
    //根据用户id查询用户已分配的角色
    getRolesByUserId(userId) {
        return request({
            url: `${api_name}/get/${userId}`,
            method: 'get'
        })
    },
    //分配角色
    assignRoles(assginRoleVo) {
        return request({
            url: `${api_name}/post`,
            method: 'post',
            data: assginRoleVo
        })
    }
}
