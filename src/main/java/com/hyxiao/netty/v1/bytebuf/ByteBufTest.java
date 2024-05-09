package com.hyxiao.netty.v1.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class ByteBufTest {

    private static void print(String action, ByteBuf buffer) {
        System.out.println("after ==================" + action + "==================");
        System.out.println("容量 capacity(): " + buffer.capacity());
        System.out.println("最大容量 maxCapacity(): " + buffer.maxCapacity());
        System.out.println("当前的读指针 readerIndex(): " + buffer.readerIndex());
        System.out.println("ByteBuf当前可读的字节数 readableBytes(): " + buffer.readableBytes());
        System.out.println("是否可读 isReadable(): " + buffer.isReadable());
        System.out.println("当前的写指针 writerIndex(): " + buffer.writerIndex());
        System.out.println("ByteBuf当前可写的字节数 writableBytes(): " + buffer.writableBytes());
        System.out.println("是否可写 isWritable(): " + buffer.isWritable());
        System.out.println("可写的最大字节数 maxWritableBytes(): " + buffer.maxWritableBytes());
    }

    public static void main(String[] args) {

        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9, 100);

        print("allocate ByteBuf(9, 100)", buffer);

        //  write 方法改变写指针，写完之后写指针未到 capacity 的时候，buffer 仍然可写
        //  写入{1, 2, 3, 4}后，写指针移动到4，表示有4个字节被写入。此时可读字节数变为4，可写字节数为5。
        buffer.writeBytes(new byte[]{1, 2, 3, 4});
        print("writeBytes(1,2,3,4)", buffer);

        //  write 方法改变写指针，写完之后写指针未到 capacity 的时候，buffer 仍然可写，写完 int 类型之后，写指针增加 4
        //  整数占用4个字节，所以写指针移动到8。此时可读字节数变为8，可写字节数为1。
        buffer.writeInt(12);
        print("writeInt(12)", buffer);

        //  write 方法改变写指针，写完之后写指针等于 capacity 的时候，buffer 不可写
        //  写指针移动到9，这是容量的极限。此时可读字节数为9，没有更多空间可写。
        buffer.writeBytes(new byte[]{5});
        print("writeBytes(5)", buffer);

        //  write 方法改变写指针，写的时候发现 buffer 不可写则开始扩容，扩容之后 capacity 随即改变
        buffer.writeBytes(new byte[]{6});
        print("writeBytes(6)", buffer);

        //  get 方法不改变读写指针
        //  当前数据布局如下
        //  Index:  0  1  2  3  4  5  6  7  8
        //  Data:   [1, 2, 3, 4, 0, 0, 0, 12, 5]
        //  getByte(3) 返回的索引为3的单个字节，索引3的数据是 `4`
        System.out.println("getByte(3) return: " + buffer.getByte(3));
        //  在计算机中，每个字节由8位组成，可以表示256个不同的值（从0到255）。因此，当将字节转换为更大的数值时，每个字节的“权重”或“倍数”是基于256的幂次方。
        //  short 是16位，即2个字节，对于两个字节的数值，第一个字节的权重是 256^1（或256），第二个字节的权重是 256^0（或1）。所以 256^1 * 4 + 256 * 0 = 1024
        System.out.println("getShort(3) return: " + buffer.getShort(3));
        //  int 是32位，即4个字节，对于4个字节的数值，字节权重分别是是 256^3、256^2、256^1、256^0。所以 256^3 * 4 + 256^2 * 0 + 256^ 1 * 0 + 256^0 * 0 = 67108864
        System.out.println("getInt(3) return: " + buffer.getInt(3));
        print("getByte()", buffer);
    }

}
