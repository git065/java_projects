package com.adslur.szt.exeption;

public final class ErrorInputText {

	public ErrorInputText() {
	}

	/*
	 * column DEMANDS_UR.MSISDN is 'Номер телефона'; column DEMANDS_UR.EMAIL is
	 * 'Адрес электронной почты '; column DEMANDS_UR.FAMDOP is 'Фамилия
	 * доверенного лица'; column DEMANDS_UR.IMDOP is 'Имя доверенного лица ';
	 * column DEMANDS_UR.OTDOP is 'Отчество доверенного лица '; column
	 * DEMANDS_UR.START_DATE is 'Дата/время начала действия записи'; column
	 * DEMANDS_UR.NTDOP is 'Контактный телефон'; column DEMANDS_UR.FAX is
	 * 'Факс'; column DEMANDS_UR.MINI_ATS is 'Наличие Мини-АТС у клиента (Y/N)';
	 * column DEMANDS_UR.MINI_ATS_SERVICE is 'Контактная информация лица,
	 * обслуживающего Мини-АТС'; column DEMANDS_UR.LOC_NET_QUANTITY is
	 * 'Количество компьютеров в локальной сети клиента'; column
	 * DEMANDS_UR.REG_NET is 'Наличие местной сети у клиента (Y/N)'; column
	 * DEMANDS_UR.REG_NET_OWN is 'Контактная информация владельца местной сети';
	 * column DEMANDS_UR.FAMRESP is 'Фамилия лица, ответственного за подписание
	 * Акта приема-передачи обрудования'; column DEMANDS_UR.IMRESP is 'Имя лица,
	 * ответственного за подписание Акта приема-передачи обрудования'; column
	 * DEMANDS_UR.OTRESP is 'Отчество лица, ответственного за подписание Акта
	 * приема-передачи обрудования'; column DEMANDS_UR.ID_VPN is 'Выделенный
	 * доступ(INET)/Передача данных(VPN) - ссылка на dtd.vpn_tbl'; column
	 * DEMANDS_UR.ID_ACS is 'Технология доступа(ADSL по умолчанию)'; column
	 * DEMANDS_UR.ID_ITF is 'Интерфейс доступа(Ethernet по умолчанию)'; column
	 * DEMANDS_UR.ID_SRV is 'Сервис/тарифный план. Ссылка на dtd.srv_tbl';
	 * column DEMANDS_UR.IP_QUAN is 'Количество IP-адресов'; column
	 * DEMANDS_UR.FAMOPER is 'Фамилия торгового представителя/оператора ЦОК,
	 * заполнявшего форму заказа услуги'; column DEMANDS_UR.IMOPER is 'Имя
	 * торгового представителя/оператора ЦОК, заполнявшего форму заказа услуги';
	 * column DEMANDS_UR.OTOPER is 'Отчество торгового представителя/оператора
	 * ЦОК, заполнявшего форму заказа услуги'; column DEMANDS_UR.NAVI_USER is
	 * 'Код оператора';
	 */
	public static final String msgTlf = "Не заполнено поле < № Телефона> ";
	public static final String msgEmail = "Не заполнено поле < Email > ";
	public static final String msgFamDov = "Не заполнено поле <Фамилия доверенного лица > ";
	public static final String msgImDov = "Не заполнено поле <Имя доверенного лица > ";
	public static final String msgOtDov = "Не заполнено поле <Отчество доверенного лица > ";
	public static final String msgNtDop = "Не заполнено поле   Допонительная информация   <Телефон > ";
	public static final String msgFax = "Не заполнено поле   Допонительная информация   <Факс > ";
	public static final String msgFamResp = "Не заполнено поле   Ответственный представитель <Фамилия  > ";
	public static final String msgImResp = "Не заполнено поле   Ответственный представитель <Имя  > ";
	public static final String msgOtResp = "Не заполнено поле   Ответственный представитель <Отчество  > ";
	public static final String msgCountKomp = "Неправильно заполнено поле <кол-во компьютеров>. "
			+ "\n" + "Формат заполнения : число";
	public static final String msgTarif = " Вы не выбрали тарифный план ";
	public static final String msgTempOk = " Нет необходимости проводить бронирование"
			+ "\n" + "   - оборудование уже времено забронировано ";

}