package com.ssm.template.realm;

import com.ssm.template.entity.User;
import com.ssm.template.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyRealm extends AuthorizingRealm {

    UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 认证
        // 从token拿到用户名
        String username = token.getPrincipal().toString();
        // 从用户名拿到User对象
        User user = service.getUserByUsername(username);

        if (user != null) {
            // 验证密码在内部
            // 设置加盐验证
            return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
        }
        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 授权
        User user = (User) principals.getPrimaryPrincipal();
        String role = service.getRoleByUid(user.getUid());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (role != null && role.length() > 0) {
            // 添加角色
            info.addRole(role);
        }
        return info;
    }

}
