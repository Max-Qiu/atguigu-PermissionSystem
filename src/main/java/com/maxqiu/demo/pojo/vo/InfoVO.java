package com.maxqiu.demo.pojo.vo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.maxqiu.demo.entity.Menu;
import com.maxqiu.demo.entity.User;
import com.maxqiu.demo.enums.MenuTypeEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户信息
 *
 * @author Max_Qiu
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class InfoVO {
    /**
     * 用户名称
     */
    private String name;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户菜单
     */
    private List<RouterVO> routers;

    /**
     * 用户按钮
     */
    private List<String> buttons;

    public InfoVO(User user, List<Menu> list) {
        this.name = user.getName();
        this.avatar = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
        this.routers = buildRouters(list, 0);
        this.buttons = list.stream().filter(e -> e.getType().equals(MenuTypeEnum.BUTTON)).map(Menu::getPerms).toList();
    }

    /**
     * 根据菜单构建路由
     *
     * @param menus
     * @return
     */
    private List<RouterVO> buildRouters(List<Menu> menus, Integer parentId) {
        List<RouterVO> routers = new LinkedList<>();
        for (Menu menu : menus.stream().filter(e -> e.getParentId().equals(parentId)).toList()) {
            RouterVO router = new RouterVO(menu);
            router.setHidden(false);
            router.setAlwaysShow(false);
            List<Menu> children = menus.stream().filter(e -> e.getParentId().equals(menu.getId())).toList();
            // 如果当前是菜单，需将按钮对应的路由加载出来，如：“角色授权”按钮对应的路由在“系统管理”下面
            if (menu.getType().equals(MenuTypeEnum.MENU)) {
                List<Menu> hiddenMenuList = children.stream().filter(item -> StringUtils.hasText(item.getComponent())).toList();
                for (Menu hiddenMenu : hiddenMenuList) {
                    RouterVO hiddenRouter = new RouterVO(hiddenMenu);
                    hiddenRouter.setHidden(true);
                    hiddenRouter.setAlwaysShow(false);
                    routers.add(hiddenRouter);
                }
            } else {
                if (!CollectionUtils.isEmpty(children)) {
                    if (children.size() > 0) {
                        router.setAlwaysShow(true);
                    }
                    router.setChildren(buildRouters(menus, menu.getId()));
                }
            }
            routers.add(router);
        }
        return routers;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    private static class RouterVO {
        /**
         * 路由名字
         */
        private String name;

        /**
         * 路由地址
         */
        private String path;

        /**
         * 是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现
         */
        private Boolean hidden;

        /**
         * 组件地址
         */
        private String component;

        /**
         * 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
         */
        private Boolean alwaysShow;

        /**
         * 其他元素
         */
        private MetaVO meta;

        /**
         * 子路由
         */
        private List<RouterVO> children;

        public RouterVO(Menu menu) {
            this.setHidden(false);
            this.setPath((menu.getParentId() == 0 ? "/" : "") + menu.getPath());
            this.setComponent(menu.getComponent());
            this.setMeta(new MetaVO(menu.getName(), menu.getIcon()));
        }

        @Getter
        @Setter
        @NoArgsConstructor
        @ToString
        private static class MetaVO {
            /**
             * 设置该路由在侧边栏和面包屑中展示的名字
             */
            private String title;

            /**
             * 设置该路由的图标，对应路径src/assets/icons/svg
             */
            private String icon;

            public MetaVO(String title, String icon) {
                this.title = title;
                this.icon = icon;
            }
        }
    }
}
