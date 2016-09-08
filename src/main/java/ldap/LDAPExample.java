package ldap;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Arrays;
import java.util.Hashtable;

public class LDAPExample {
    /**
     * method to connect LDAP
     *
     * @return LdapContext Object
     * @throws NamingException
     */
    public LdapContext connetLDAP() throws NamingException {
        String ldapFactory = "com.sun.jndi.ldap.LdapCtxFactory";
        String ldapUrl = "ldap:/IP:port";
        String ldapAccount = "cn=root";
        String ldapPwd = "password";

        Hashtable<String, Object> env = new Hashtable<String, Object>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, ldapFactory);
        env.put(Context.PROVIDER_URL, ldapUrl);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, ldapAccount);
        env.put(Context.SECURITY_CREDENTIALS, ldapPwd);
        env.put("java.naming.referral", "follow");

        return new InitialLdapContext(env, null);
    }

    public void testAdd() throws Exception {
        LdapContext ctx = connetLDAP();
        Attributes attrs = new BasicAttributes(true);
        Attribute objclass = new BasicAttribute("objectclass");
        String[] attrObjectClassPerson = {"inetOrgPerson", "organizationalPerson", "person", "top"};
        Arrays.sort(attrObjectClassPerson);
        for (String ocp : attrObjectClassPerson) {
            objclass.add(ocp);
        }
        attrs.put(objclass);
        String uid = "zhangsan";
        String userDN = "uid=" + uid + "," + "cn=users,dc=cas,dc=mydc";
        attrs.put("cn", uid);
        attrs.put("sn", uid);
        attrs.put("displayName", "张三");
        attrs.put("mail", "abc@163.com");
        attrs.put("description", "");
        attrs.put("userPassword", "Passw0rd".getBytes("UTF-8"));
        ctx.createSubcontext(userDN, attrs);
    }

    public void testRemove() throws Exception {
        LdapContext ctx = connetLDAP();
        String uid = "zhangsan";
        String userDN = "uid=" + uid + "," + "cn=users,dc=cas,dc=mydc";
        ctx.destroySubcontext(userDN);
    }

    public void testModify() throws Exception {
        LdapContext ctx = connetLDAP();
        String uid = "zhangsan";
        String userDN = "uid=" + uid + "," + "cn=users,dc=cas,dc=mydc";
        Attributes attrs = new BasicAttributes(true);
        attrs.put("mail", "zhangsan@163.com");
        ctx.modifyAttributes(userDN, DirContext.REPLACE_ATTRIBUTE, attrs);
    }

    public void testSearch() throws Exception {
        LdapContext ctx = connetLDAP();
        // 设置过滤条件
        String uid = "zhangsan";
        String filter = "(&(objectClass=top)(objectClass=organizationalPerson)(uid=" + uid + "))";
        // 限制要查询的字段内容
        String[] attrPersonArray = {"uid", "userPassword", "displayName", "cn", "sn", "mail", "description"};
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        // 设置将被返回的Attribute
        searchControls.setReturningAttributes(attrPersonArray);
        // 三个参数分别为：
        // 上下文；
        // 要搜索的属性，如果为空或 null，则返回目标上下文中的所有对象；
        // 控制搜索的搜索控件，如果为 null，则使用默认的搜索控件
        NamingEnumeration<SearchResult> answer = ctx.search("cn=users,dc=cas,dc=mydc", filter, searchControls);
        // 输出查到的数据
        while (answer.hasMore()) {
            SearchResult result = answer.next();
            NamingEnumeration<? extends Attribute> attrs = result.getAttributes().getAll();
            while (attrs.hasMore()) {
                Attribute attr = attrs.next();
                System.out.println(attr.getID() + "=" + attr.get());
            }
            System.out.println("============");
        }
    }
}
