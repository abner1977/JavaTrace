package com.abner.study;

import java.util.Objects;
import java.util.Stack;

/**
 * @Description:
 * @auther: wangyizheng
 * @date: 2019/3/21 17:03
 */
public class ListNodeDone {


/*反转*/
    public ListNode reverse(ListNode node){

        ListNode next=null;//临时变量 用于下次循环
        ListNode tail=null;//链表尾巴
        while(null!=node){
            next=node.next; // 1定义临时变量  为什么不在最后一步 直接 node =node.next  此时 node.next 在步骤2 已经变化

            node.next=tail;//2将当前节点 指向尾巴对象  倒序嘛   这一步 是每次循环连接节点的操作
            tail=node;// 3然后尾巴 重新赋值  取当前节点 作为新的尾巴


            node=next;//4

            /*1 和 4  用于保证while循环 一直 往下一个节点循环*/
            /*2 和3
            *
            * 1.next->null 此时  尾巴为空  1指向null后   1 作为新的尾巴   节点1
            *
            * 2.next->1  此时  尾巴为1   2指向1后  2 作为新的尾巴    节点2 ->节点1
            *
            *
            *  类推  节点5->4>3>2>1
            * */

        }
        return tail;

    }

/*获取链表 倒数 第 K个 节点  栈处理 */
    public ListNode findKNodeFromTail(ListNode node,int k){
         if(node==null||k<=0){
             return null;
         }
         int count=0;// 节点个数
        Stack<ListNode>  nodes=new Stack<ListNode>();
        nodes.add(node);
         while(null!=node){
             node=node.next;
             if(node!=null){
                 nodes.add(node);
             }
             count++;
         }
        System.out.println("链表节点数：cout="+count);
        System.out.println("栈深度="+nodes.size());//栈的倒数第K个节点 就是 正数 第nodes.size()-k+1  个节点  也就是 nodes.get(nodes.size()-k)
        System.out.println("倒数第"+k+"个节点的值："+nodes.get(nodes.size()-k).val);
         return nodes.get(nodes.size()-k);
    }

    /*获取链表 倒数 第 K个 节点  */
    public  ListNode findKNodeFromTail2(ListNode node ,int k){
        /*
        * 分析：
        * 设节点总数 L ,节点的倒数第K个节点 即为正数 第 L-K+1个节点
        * 场景：
        * 指定两种节点  node1先跑 等节点 为 K-1 时   node2开始跑  等node1达到尾部  node2节点 就是 L-K+1
        * */

        ListNode node1=node,node2=node;
        ListNode expectNode = null;
        boolean flag=false;//node2 启动标识
        int  count=0;//节点个数
        while(node1!=null){
            if(count==(k-1)){
                flag=true;//启动
            }
            node1=node1.next;
            if(node1==null){//到尾部了
                expectNode= node2;//得到 期望 node节点
            }
            if(flag){
                node2=node2.next;
            }
            count++;
        }
        if(count<k){
            System.out.println("返回null");
            return null;
        }
        System.out.println("倒数第"+k+"个节点的值："+expectNode.val);
        return expectNode;
    }


    /*删除链表的倒数第 n 个节点，并且返回链表的头结点。*/
    public ListNode  deleteKNodeFromTail(ListNode node,int k){
        /*
         * 分析：
         * 设节点总数 L ,节点的倒数第K个节点 即为正数 第 L-K+1个节点
         * 场景：
         * 指定两种节点  node1先跑 等节点 为 K-1 时   node2开始跑  等node1达到尾部  node2节点 就是 L-K+1
         * */
         ListNode dummy=new ListNode(0);//指定哑节点
        ListNode node1=node,node2=node,node2temp=null;
        dummy.next=node2;//指向头节点 用于 输出结果
        ListNode expectNode = null;
        boolean flag=false;//node2 启动标识
        int  count=0;//节点个数
        while(node1!=null){
            if(count==(k-1)){
                flag=true;//启动
            }
            node1=node1.next;
            if(node1==null){//到尾部了
                node2temp.next= node2.next;// node2temp 是期望节点上一个   指向 node2下一个
            }
            if(flag){
                node2temp=node2;// 指定临时节点 也就是 到尾部时 k-1节点
                node2=node2.next;
            }
            count++;
        }
        if(count<k){
            System.out.println("返回null");
            return null;
        }
        return dummy.next;



    }

    public static void main(String[] args) {
        //新建节点
        ListNode a=new ListNode(1);
        ListNode b=new ListNode(2);
        ListNode c=new ListNode(7);
        ListNode d=new ListNode(4);
        ListNode e=new ListNode(5);
        ListNode f=new ListNode(6);
        //连接节点
        a.next=b;
        b.next=c;
        c.next=d;
        d.next=e;
        e.next=f;

//        System.out.println("**********************反转***************************");
//        new ListNodeDone().reverse(a);
//        while(f!=null){
//            System.out.print(f.val+"->");
//            f=  f.next;
//        }
//        System.out.println("**********************反转***************************");
//        System.out.println();

           //查找
//        new ListNodeDone().findKNodeFromTail2(a,4);

        //删除
        new  ListNodeDone().deleteKNodeFromTail(a,2);
        while(a!=null){
            System.out.print(a.val+"->");
            a=  a.next;
        }


    }
}
