create database management;

use management;

create table product(
`product_id` varchar(32) not null ,
`product_type` varchar(32) not null comment '型号',
`product_size` varchar(32) not null comment '规格',
`product_direction` int not null comment '开向',
`product_price` int not null comment '单价',
`product_stock` int not null comment '库存',
`create_time` timestamp default current_timestamp comment '创建时间',
`update_time` timestamp default current_timestamp on update current_timestamp comment '更新时间',
primary key (`product_id`)
) engine=InnoDB default charset=utf8 comment '商品表';

create table order_master(
`order_id` varchar(32) not null ,
`buyer_address` varchar(64) not null comment '顾客地址',
`buyer_name` varchar(32) not null comment '顾客姓名',
`buyer_phone` varchar(32) not null comment '顾客联系方式',
`installer_id` int not null default 0 comment '安装人员',
`install_time` timestamp not null comment '安装日期',
`order_deposit` int not null comment '订单订金',
`order_amount` int not null comment '订单总价',
`order_status` int not null default 0 comment '订单状态',
`create_time` timestamp default current_timestamp comment '创建时间',
`update_time` timestamp default current_timestamp on update current_timestamp comment '更新时间',
primary key (`order_id`)
) engine=InnoDB default charset=utf8 comment '订单表';

create table order_detail(
`detail_id` varchar(32) not null,
`order_id` varchar(32) not null,
`product_id` varchar(32) not null,
`product_type` varchar(32) not null comment '型号',
`product_size` varchar(32) not null comment '规格',
`product_direction` int not null comment '开向',
`product_price` int not null comment '单价',
`product_quantity` int not null comment '数量',
`create_time` timestamp default current_timestamp comment '创建时间',
`update_time` timestamp default current_timestamp on update current_timestamp comment '更新时间',
primary key (`detail_id`)
) engine=InnoDB default charset=utf8 comment '订单详情表';

create table installer(
`installer_id` int not null auto_increment,
`installer_name` varchar(64) not null comment '安装人员姓名',
`installer_phone` varchar(32) not null comment '安装人员联系方式',
`create_time` timestamp default current_timestamp comment '创建时间',
`update_time` timestamp default current_timestamp on update current_timestamp comment '更新时间',
primary key (`installer_id`)
) engine=InnoDB default charset=utf8 comment '安装人员表';