package com.zive.appendSQL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppendSql {

	public static void main(String[] args) {
		String sql = "Preparing: select sum(a.store) 'store', sum(a.pos) 'pos', sum(a.cash) 'cash', sum(a.point) 'point', sum(a.shouldPay) 'shouldPay', sum(a.realPay) 'realPay', sum(a.owe) 'owe', sum(a.refund)'refund', a.shopId, b.`name` 'shopName', GROUP_CONCAT(a.orderIds) 'orderId', a.type 'type' from ( select sum(ifnull(aa.store_pay,0)) 'store', sum(ifnull(aa.bankcard_pay,0)) 'pos', sum(ifnull(aa.cash_pay,0)) 'cash', sum(ifnull(aa.point_pay,0)) 'point', sum(ifnull(aa.payment,0)) 'shouldPay', sum(ifnull(aa.real_payment,0)) 'realPay', sum(ifnull(aa.buy_owe,0)) 'owe', sum(0)'refund', b.shop_id 'shopId', GROUP_CONCAT(aa.id) 'orderIds', 'project' as 'type' from shop_s_consumption_project a INNER JOIN `shop_s_consumption_project_detail` aa ON a.`consumption_id` = aa.`consumption_id` AND a.id = aa.`consumption_project_id` inner join shop_s_consumption b on a.consumption_id = b.id inner join shop_s_member_card c on c.id = b.member_card_id inner join shop_s_member_card_386_plan t on c.id = t.member_card_id WHERE b.`status` = ? and (t.is_386_pass = ? or c.is_pass = ? or c.is_new_pass = ?) and (t.pass_386_time <= b.consumption_date or c.pass_time <= b.consumption_date or c.new_pass_time <= b.consumption_date) and aa.project_id in ( ? , ? , ? , ? , ? , ? , ? , ? , ? ) and (aa.activity_id = 'tuiguang' OR aa.experience_price = aa.promotion_price) and b.shop_id in ( ? , ? , ? ) and b.consumption_date >= ? and b.consumption_date <= ? GROUP BY b.shop_id union all select sum(ifnull(a.store_pay,0)) 'store', sum(ifnull(a.bankcard_pay,0)) 'pos', sum(ifnull(a.cash_pay,0)) 'cash', 0 'point', sum(ifnull(a.payment,0)) 'shouldPay', sum(ifnull(a.real_payment,0)) 'realPay', sum(ifnull(a.buy_owe,0)) 'owe', sum(0)'refund', b.shop_id 'shopId', GROUP_CONCAT(b.id) 'orderIds', 'set' as 'type' from shop_s_consumption_set a inner join shop_s_consumption b on a.consumption_id = b.id and a.is_detail_pay = 0 inner join shop_s_member_card c on c.id = b.member_card_id inner join shop_s_member_card_386_plan t on c.id = t.member_card_id inner join shop_s_consumption_set_detail d on d.consumption_set_id = a.id left join shop_b_activity_info e on e.id = d.activity_id WHERE b.`status` = ? and (t.is_386_pass = ? or c.is_pass = ? or c.is_new_pass = ?) and (t.pass_386_time <= b.consumption_date or c.pass_time <= b.consumption_date or c.new_pass_time <= b.consumption_date) and d.activity_id in ( ? , ? , ? , ? , ? ) AND (e.name IS NULL OR e.name NOT LIKE CONCAT(?,'%')) and e.act_type = 1 and b.shop_id in ( ? , ? , ? ) and b.consumption_date >= ? and b.consumption_date <= ? group by b.shop_id union all select sum(f.store_pay) 'store', sum(f.bankcard_pay) 'pos', sum(f.cash_pay) 'cash', sum(f.point_pay) 'point', sum(f.payment) 'shouldPay', sum(f.real_payment) 'realPay', sum(f.buy_owe) 'owe', sum(0)'refund', b.shop_id 'shopId', GROUP_CONCAT(b.id) 'orderIds', 'set' as 'type' from shop_s_consumption_set a inner join shop_s_consumption b on a.consumption_id = b.id and a.is_detail_pay = 1 inner join shop_s_member_card c on c.id = b.member_card_id inner join shop_s_member_card_386_plan t on c.id = t.member_card_id inner join shop_s_consumption_set_detail d on d.consumption_set_id = a.id left join shop_b_activity_info e on e.id = d.activity_id inner join shop_s_consumption_project_detail f on f.consumption_set_id = a.id AND f.`activity_id` = d.`activity_id` AND f.buy_type = d.buy_type LEFT JOIN shop_b_project_info ee ON ee.id = f.project_id WHERE b.`status` = ? and (t.is_386_pass = ? or c.is_pass = ? or c.is_new_pass = ?) and (t.pass_386_time <= b.consumption_date or c.pass_time <= b.consumption_date or c.new_pass_time <= b.consumption_date) and d.activity_id in ( ? , ? , ? , ? , ? ) AND (e.name IS NULL OR e.name NOT LIKE CONCAT(?,'%')) AND (ee.name IS NULL OR ee.name NOT LIKE CONCAT(?,'%')) and b.shop_id in ( ? , ? , ? ) and b.consumption_date >= ? and b.consumption_date <= ? group by b.shop_id UNION ALL SELECT sum(IFNULL(a.store_pay, 0)) 'store', sum(IFNULL(a.bankcard_pay, 0)) 'pos', sum(IFNULL(a.cash_pay, 0)) 'cash', sum(0) 'point', sum(0) 'shouldPay', sum(0) 'realPay', sum(0)'owe', sum(0)'refund', a.shop_id 'shopId', '' as 'orderIds', 'return' AS 'type' FROM shop_s_consumption_return a LEFT JOIN shop_s_consumption_project_detail k ON k.id = a.detail_id INNER JOIN shop_s_consumption b ON k.consumption_id = b.id LEFT JOIN oa_department_structure d ON d.id = a.shop_id LEFT JOIN shop_s_member_card c ON c.id = a.member_card_id inner join shop_s_member_card_386_plan t on c.id = t.member_card_id LEFT JOIN shop_b_activity_info e ON e.id = k.activity_id LEFT JOIN shop_b_project_info ee ON ee.id = k.project_id WHERE a.detail_id IS NOT NULL AND k.id IS NOT NULL and b.`status` = ? and (t.is_386_pass = ? or c.is_pass = ? or c.is_new_pass = ?) and (t.pass_386_time <= b.consumption_date or c.pass_time <= b.consumption_date or c.new_pass_time <= b.consumption_date) AND ( k.activity_id in ( ? , ? , ? , ? , ? ) OR ( (k.activity_id = 'tuiguang' OR k.experience_price = k.promotion_price) and k.project_id in ( ? , ? , ? , ? , ? , ? , ? , ? , ? ) ) ) AND (e.name IS NULL OR e.name NOT LIKE CONCAT(?,'%')) AND (ee.name IS NULL OR ee.name NOT LIKE CONCAT(?,'%')) and a.shop_id in ( ? , ? , ? ) and a.return_date >= ? and a.return_date <= ? AND a.is_fail = 0 GROUP BY a.shop_id UNION ALL SELECT sum(0) 'store', sum(0) 'pos', sum(0) 'cash', sum(0) 'point', sum(0) 'shouldPay', sum(0) 'realPay', sum(0)'owe', sum(IFNULL(b.return_money, 0)) 'refund', a.shop_id 'shopId', '' as 'orderIds', 'refund' AS 'type' FROM shop_s_return_transfer a INNER JOIN shop_s_return_transfer_detail b ON a.type = 1 AND b.type = 1 AND a.id = b.return_transfer_id LEFT JOIN shop_s_consumption_project_detail c ON c.id = b.detail_id LEFT JOIN shop_s_member_card d ON d.id = a.member_card_id inner join shop_s_member_card_386_plan t on d.id = t.member_card_id LEFT JOIN shop_b_activity_info e ON e.id = c.activity_id LEFT JOIN shop_b_project_info ee ON ee.id = c.project_id WHERE a.is_reurn_bank = 1 and a.check_status = ? AND ( c.activity_id in ( ? , ? , ? , ? , ? ) OR ( (c.activity_id = 'tuiguang' OR c.experience_price = c.promotion_price) and c.project_id in ( ? , ? , ? , ? , ? , ? , ? , ? , ? ) ) ) AND (e.name IS NULL OR e.name NOT LIKE CONCAT(?,'%')) AND (ee.name IS NULL OR ee.name NOT LIKE CONCAT(?,'%')) and a.shop_id in ( ? , ? , ? ) and a.check_date >= ? and a.check_date <= ? GROUP BY a.shop_id ) a left join user_account b on a.shopId = b.id group by a.shopId,a.`type`,b.`name`";
		String params = "1(Integer), 1(Integer), 1(Integer), 1(Integer), 加上这个值，反正查询被过滤(String), dab56ea7-db00-412a-a897-74c3767950d1(String), 0949a9d8-8cd1-4022-bd30-347ba079d476(String), 3c524d0c-d020-4798-bb95-cc7b25e0141d(String), 73c20df5-d46f-45c6-8537-6a12acbbc808(String), 91c9e673-9c97-4d1d-8e81-195fd75f5fba(String), 299eb5b5-7182-493c-8d22-23c7751e31ab(String), db67245c-0941-4cc6-81e1-3c93f4d9e7ed(String), 4396754e-c2a9-448c-8257-378891dcdff2(String), 110188(String), 190325(String), 1e7a052d-c88c-4971-814a-f587bf566e96(String), 2021-04-01 00:00:00.0(Timestamp), 2021-04-30 23:59:59.0(Timestamp), 1(Integer), 1(Integer), 1(Integer), 1(Integer), eb3f3c7b-fe6f-4b8e-ae6e-f83f6be71270(String), 375f308f-a7b4-4deb-b25d-261244d5648e(String), f7a219b8-6649-420c-94fd-6d5007bc2ee6(String), 4b1fed4f-4d46-4320-841d-cf956439e041(String), 24e27621-4bb4-4fba-b1ba-bda7dafd50c9(String), 灵秀(String), 110188(String), 190325(String), 1e7a052d-c88c-4971-814a-f587bf566e96(String), 2021-04-01 00:00:00.0(Timestamp), 2021-04-30 23:59:59.0(Timestamp), 1(Integer), 1(Integer), 1(Integer), 1(Integer), eb3f3c7b-fe6f-4b8e-ae6e-f83f6be71270(String), 375f308f-a7b4-4deb-b25d-261244d5648e(String), f7a219b8-6649-420c-94fd-6d5007bc2ee6(String), 4b1fed4f-4d46-4320-841d-cf956439e041(String), 24e27621-4bb4-4fba-b1ba-bda7dafd50c9(String), 灵秀(String), 灵秀(String), 110188(String), 190325(String), 1e7a052d-c88c-4971-814a-f587bf566e96(String), 2021-04-01 00:00:00.0(Timestamp), 2021-04-30 23:59:59.0(Timestamp), 1(Integer), 1(Integer), 1(Integer), 1(Integer), eb3f3c7b-fe6f-4b8e-ae6e-f83f6be71270(String), 375f308f-a7b4-4deb-b25d-261244d5648e(String), f7a219b8-6649-420c-94fd-6d5007bc2ee6(String), 4b1fed4f-4d46-4320-841d-cf956439e041(String), 24e27621-4bb4-4fba-b1ba-bda7dafd50c9(String), 加上这个值，反正查询被过滤(String), dab56ea7-db00-412a-a897-74c3767950d1(String), 0949a9d8-8cd1-4022-bd30-347ba079d476(String), 3c524d0c-d020-4798-bb95-cc7b25e0141d(String), 73c20df5-d46f-45c6-8537-6a12acbbc808(String), 91c9e673-9c97-4d1d-8e81-195fd75f5fba(String), 299eb5b5-7182-493c-8d22-23c7751e31ab(String), db67245c-0941-4cc6-81e1-3c93f4d9e7ed(String), 4396754e-c2a9-448c-8257-378891dcdff2(String), 灵秀(String), 灵秀(String), 110188(String), 190325(String), 1e7a052d-c88c-4971-814a-f587bf566e96(String), 2021-04-01 00:00:00.0(Timestamp), 2021-04-30 23:59:59.0(Timestamp), 1(Integer), eb3f3c7b-fe6f-4b8e-ae6e-f83f6be71270(String), 375f308f-a7b4-4deb-b25d-261244d5648e(String), f7a219b8-6649-420c-94fd-6d5007bc2ee6(String), 4b1fed4f-4d46-4320-841d-cf956439e041(String), 24e27621-4bb4-4fba-b1ba-bda7dafd50c9(String), 加上这个值，反正查询被过滤(String), dab56ea7-db00-412a-a897-74c3767950d1(String), 0949a9d8-8cd1-4022-bd30-347ba079d476(String), 3c524d0c-d020-4798-bb95-cc7b25e0141d(String), 73c20df5-d46f-45c6-8537-6a12acbbc808(String), 91c9e673-9c97-4d1d-8e81-195fd75f5fba(String), 299eb5b5-7182-493c-8d22-23c7751e31ab(String), db67245c-0941-4cc6-81e1-3c93f4d9e7ed(String), 4396754e-c2a9-448c-8257-378891dcdff2(String), 灵秀(String), 灵秀(String), 110188(String), 190325(String), 1e7a052d-c88c-4971-814a-f587bf566e96(String), 2021-04-01 00:00:00.0(Timestamp), 2021-04-30 23:59:59.0(Timestamp)";
	
		List<String> asList = new ArrayList<>(Arrays.asList(params.split(",")));
		
		while (sql.contains("?")) {
			String remove = asList.remove(0);
			String[] split = remove.split("\\(");
			remove = split[0].trim();
			String type = split[1].trim();
			if(type.contains("String") || type.contains("Timestamp")){
				remove = "'" + remove + "'";
			}
			sql = sql.replaceFirst("\\?", remove);
		}
		
		System.out.println(sql);
	}
}