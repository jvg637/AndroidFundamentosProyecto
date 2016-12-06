package es.maps.programacion.fundamentos.es.androidfundamentosproyecto.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.Vector;

import es.maps.programacion.fundamentos.es.androidfundamentosproyecto.sqlite.pojo.Divisa;
import es.maps.programacion.fundamentos.es.androidfundamentosproyecto.sqlite.pojo.Pais;


/**
 * Created by jvg63 on 14/11/2016.
 */

public class PaisesDivisasSQLite extends SQLiteOpenHelper {
    private Context context;

    public PaisesDivisasSQLite(Context context) {
        super(context, "paises_divisas", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("DROP TABLE IF EXISTS divisas");
db.execSQL("CREATE TABLE divisas (ID_DIVISA  PRIMARY KEY  NOT NULL  UNIQUE , ID_DIVISA2 , DIVISA_ES , DIVISA_EN )");
db.execSQL("INSERT INTO divisas VALUES('AED','784','Dírham de los Emiratos Árabes Unidos','United Arab Emirates dirham')");
db.execSQL("INSERT INTO divisas VALUES('AFN','971','Afgani','Afghan afghani')");
db.execSQL("INSERT INTO divisas VALUES('ALL','8','Lek','Albanian lek')");
db.execSQL("INSERT INTO divisas VALUES('AMD','51','Dram armenio','Armenian dram')");
db.execSQL("INSERT INTO divisas VALUES('ANG','532','Florín antillano neerlandés','Netherlands Antillean guilder')");
db.execSQL("INSERT INTO divisas VALUES('AOA','973','Kwanza','Angolan kwanza')");
db.execSQL("INSERT INTO divisas VALUES('ARS','32','Peso argentino','Argentine peso')");
db.execSQL("INSERT INTO divisas VALUES('AUD','36','Dólar australiano','Australian dollar')");
db.execSQL("INSERT INTO divisas VALUES('AWG','533','Florín arubeño','Aruban florin')");
db.execSQL("INSERT INTO divisas VALUES('AZN','944','Manat azerbaiyano','Azerbaijani manat')");
db.execSQL("INSERT INTO divisas VALUES('BAM','977','Marco convertible','Bosnia and Herzegovina convertible mark')");
db.execSQL("INSERT INTO divisas VALUES('BBD','52','Dólar de Barbados','Barbados dollar')");
db.execSQL("INSERT INTO divisas VALUES('BDT','50','Taka','Bangladeshi taka')");
db.execSQL("INSERT INTO divisas VALUES('BGN','975','Lev búlgaro','Bulgarian lev')");
db.execSQL("INSERT INTO divisas VALUES('BHD','48','Dinar bareiní','Bahraini dinar')");
db.execSQL("INSERT INTO divisas VALUES('BIF','108','Franco de Burundi','Burundian franc')");
db.execSQL("INSERT INTO divisas VALUES('BMD','60','Dólar bermudeño','Bermudian dollar')");
db.execSQL("INSERT INTO divisas VALUES('BND','96','Dólar de Brunéi','Brunei dollar')");
db.execSQL("INSERT INTO divisas VALUES('BOB','68','Boliviano','Boliviano')");
db.execSQL("INSERT INTO divisas VALUES('BOV','984','MVDOL','Bolivian Mvdol (funds code)')");
db.execSQL("INSERT INTO divisas VALUES('BRL','986','Real brasileño','Brazilian real')");
db.execSQL("INSERT INTO divisas VALUES('BSD','44','Dólar bahameño','Bahamian dollar')");
db.execSQL("INSERT INTO divisas VALUES('BTN','64','Ngultrum','Bhutanese ngultrum')");
db.execSQL("INSERT INTO divisas VALUES('BWP','72','Pula','Botswana pula')");
db.execSQL("INSERT INTO divisas VALUES('BYR','974','Rublo bielorruso','Belarusian ruble')");
db.execSQL("INSERT INTO divisas VALUES('BZD','84','Dólar beliceño','Belize dollar')");
db.execSQL("INSERT INTO divisas VALUES('CAD','124','Dólar canadiense','Canadian dollar')");
db.execSQL("INSERT INTO divisas VALUES('CDF','976','Franco congoleño','Congolese franc')");
db.execSQL("INSERT INTO divisas VALUES('CHE','947','Euro WIR','WIR Euro (complementary currency)')");
db.execSQL("INSERT INTO divisas VALUES('CHF','756','Franco suizo','Swiss franc')");
db.execSQL("INSERT INTO divisas VALUES('CHW','948','Franco WIR','WIR Franc (complementary currency)')");
db.execSQL("INSERT INTO divisas VALUES('CLF','990','Unidad de fomento','Unidad de Fomento (funds code)')");
db.execSQL("INSERT INTO divisas VALUES('CLP','152','Peso chileno','Chilean peso')");
db.execSQL("INSERT INTO divisas VALUES('CNY','156','Yuan chino','Chinese yuan')");
db.execSQL("INSERT INTO divisas VALUES('COP','170','Peso colombiano','Colombian peso')");
db.execSQL("INSERT INTO divisas VALUES('COU','970','Unidad de valor real','Unidad de Valor Real (UVR) (funds code)[7]')");
db.execSQL("INSERT INTO divisas VALUES('CRC','188','Colón costarricense','Costa Rican colon')");
db.execSQL("INSERT INTO divisas VALUES('CUC','931','Peso convertible','Cuban convertible peso')");
db.execSQL("INSERT INTO divisas VALUES('CUP','192','Peso cubano','Cuban peso')");
db.execSQL("INSERT INTO divisas VALUES('CVE','132','Escudo caboverdiano','Cape Verde escudo')");
db.execSQL("INSERT INTO divisas VALUES('CZK','203','Corona checa','Czech koruna')");
db.execSQL("INSERT INTO divisas VALUES('DJF','262','Franco yibutiano','Djiboutian franc')");
db.execSQL("INSERT INTO divisas VALUES('DKK','208','Corona danesa','Danish krone')");
db.execSQL("INSERT INTO divisas VALUES('DOP','214','Peso dominicano','Dominican peso')");
db.execSQL("INSERT INTO divisas VALUES('DZD','12','Dinar argelino','Algerian dinar')");
db.execSQL("INSERT INTO divisas VALUES('EGP','818','Libra egipcia','Egyptian pound')");
db.execSQL("INSERT INTO divisas VALUES('ERN','232','Nakfa','Eritrean nakfa')");
db.execSQL("INSERT INTO divisas VALUES('ETB','230','Birr etíope','Ethiopian birr')");
db.execSQL("INSERT INTO divisas VALUES('EUR','978','Euro','Euro')");
db.execSQL("INSERT INTO divisas VALUES('FJD','242','Dólar fiyiano','Fiji dollar')");
db.execSQL("INSERT INTO divisas VALUES('FKP','238','Libra malvinense','Falkland Islands pound')");
db.execSQL("INSERT INTO divisas VALUES('GBP','826','Libra esterlina','Pound sterling')");
db.execSQL("INSERT INTO divisas VALUES('GEL','981','Lari','Georgian lari')");
db.execSQL("INSERT INTO divisas VALUES('GHS','936','Cedi ghanés','Ghanaian cedi')");
db.execSQL("INSERT INTO divisas VALUES('GIP','292','Libra de Gibraltar','Gibraltar pound')");
db.execSQL("INSERT INTO divisas VALUES('GMD','270','Dalasi','Gambian dalasi')");
db.execSQL("INSERT INTO divisas VALUES('GNF','324','Franco guineano','Guinean franc')");
db.execSQL("INSERT INTO divisas VALUES('GTQ','320','Quetzal','Guatemalan quetzal')");
db.execSQL("INSERT INTO divisas VALUES('GYD','328','Dólar guyanés','Guyanese dollar')");
db.execSQL("INSERT INTO divisas VALUES('HKD','344','Dólar de Hong Kong','Hong Kong dollar')");
db.execSQL("INSERT INTO divisas VALUES('HNL','340','Lempira','Honduran lempira')");
db.execSQL("INSERT INTO divisas VALUES('HRK','191','Kuna','Croatian kuna')");
db.execSQL("INSERT INTO divisas VALUES('HTG','332','Gourde','Haitian gourde')");
db.execSQL("INSERT INTO divisas VALUES('HUF','348','Forinto','Hungarian forint')");
db.execSQL("INSERT INTO divisas VALUES('IDR','360','Rupia indonesia','Indonesian rupiah')");
db.execSQL("INSERT INTO divisas VALUES('ILS','376','Nuevo shéquel israelí','Israeli new shekel')");
db.execSQL("INSERT INTO divisas VALUES('INR','356','Rupia india','Indian rupee')");
db.execSQL("INSERT INTO divisas VALUES('IQD','368','Dinar iraquí','Iraqi dinar')");
db.execSQL("INSERT INTO divisas VALUES('IRR','364','Rial iraní','Iranian rial')");
db.execSQL("INSERT INTO divisas VALUES('ISK','352','Corona islandesa','Icelandic króna')");
db.execSQL("INSERT INTO divisas VALUES('JMD','388','Dólar jamaiquino','Jamaican dollar')");
db.execSQL("INSERT INTO divisas VALUES('JOD','400','Dinar jordano','Jordanian dinar')");
db.execSQL("INSERT INTO divisas VALUES('JPY','392','Yen','Japanese yen')");
db.execSQL("INSERT INTO divisas VALUES('KES','404','Chelín keniano','Kenyan shilling')");
db.execSQL("INSERT INTO divisas VALUES('KGS','417','Som','Kyrgyzstani som')");
db.execSQL("INSERT INTO divisas VALUES('KHR','116','Riel','Cambodian riel')");
db.execSQL("INSERT INTO divisas VALUES('KMF','174','Franco comorense','Comoro franc')");
db.execSQL("INSERT INTO divisas VALUES('KPW','408','Won norcoreano','North Korean won')");
db.execSQL("INSERT INTO divisas VALUES('KRW','410','Won','South Korean won')");
db.execSQL("INSERT INTO divisas VALUES('KWD','414','Dinar kuwaití','Kuwaiti dinar')");
db.execSQL("INSERT INTO divisas VALUES('KYD','136','Dólar de las Islas Caimán','Cayman Islands dollar')");
db.execSQL("INSERT INTO divisas VALUES('KZT','398','Tenge','Kazakhstani tenge')");
db.execSQL("INSERT INTO divisas VALUES('LAK','418','Kip','Lao kip')");
db.execSQL("INSERT INTO divisas VALUES('LBP','422','Libra libanesa','Lebanese pound')");
db.execSQL("INSERT INTO divisas VALUES('LKR','144','Rupia de Sri Lanka','Sri Lankan rupee')");
db.execSQL("INSERT INTO divisas VALUES('LRD','430','Dólar liberiano','Liberian dollar')");
db.execSQL("INSERT INTO divisas VALUES('LSL','426','Loti','Lesotho loti')");
db.execSQL("INSERT INTO divisas VALUES('LYD','434','Dinar libio','Libyan dinar')");
db.execSQL("INSERT INTO divisas VALUES('MAD','504','Dírham marroquí','Moroccan dirham')");
db.execSQL("INSERT INTO divisas VALUES('MDL','498','Leu moldavo','Moldovan leu')");
db.execSQL("INSERT INTO divisas VALUES('MGA','969','Ariary malgache','Malagasy ariary')");
db.execSQL("INSERT INTO divisas VALUES('MKD','807','Denar','Macedonian denar')");
db.execSQL("INSERT INTO divisas VALUES('MMK','104','Kyat','Myanmar kyat')");
db.execSQL("INSERT INTO divisas VALUES('MNT','496','Tugrik','Mongolian tögrög')");
db.execSQL("INSERT INTO divisas VALUES('MOP','446','Pataca','Macanese pataca')");
db.execSQL("INSERT INTO divisas VALUES('MRO','478','Uguiya','Mauritanian ouguiya')");
db.execSQL("INSERT INTO divisas VALUES('MUR','480','Rupia de Mauricio','Mauritian rupee')");
db.execSQL("INSERT INTO divisas VALUES('MVR','462','Rufiyaa','Maldivian rufiyaa')");
db.execSQL("INSERT INTO divisas VALUES('MWK','454','Kwacha','Malawian kwacha')");
db.execSQL("INSERT INTO divisas VALUES('MXN','484','Peso mexicano','Mexican peso')");
db.execSQL("INSERT INTO divisas VALUES('MXV','979','Unidad de Inversión (UDI) mexicana','Mexican Unidad de Inversion (UDI) (funds code)')");
db.execSQL("INSERT INTO divisas VALUES('MYR','458','Ringgit malayo','Malaysian ringgit')");
db.execSQL("INSERT INTO divisas VALUES('MZN','943','Metical mozambiqueño','Mozambican metical')");
db.execSQL("INSERT INTO divisas VALUES('NAD','516','Dólar namibio','Namibian dollar')");
db.execSQL("INSERT INTO divisas VALUES('NGN','566','Naira','Nigerian naira')");
db.execSQL("INSERT INTO divisas VALUES('NIO','558','Córdoba','Nicaraguan córdoba')");
db.execSQL("INSERT INTO divisas VALUES('NOK','578','Corona noruega','Norwegian krone')");
db.execSQL("INSERT INTO divisas VALUES('NPR','524','Rupia nepalí','Nepalese rupee')");
db.execSQL("INSERT INTO divisas VALUES('NZD','554','Dólar neozelandés','New Zealand dollar')");
db.execSQL("INSERT INTO divisas VALUES('OMR','512','Rial omaní','Omani rial')");
db.execSQL("INSERT INTO divisas VALUES('PAB','590','Balboa','Panamanian balboa')");
db.execSQL("INSERT INTO divisas VALUES('PEN','604','Sol','Peruvian Sol')");
db.execSQL("INSERT INTO divisas VALUES('PGK','598','Kina','Papua New Guinean kina')");
db.execSQL("INSERT INTO divisas VALUES('PHP','608','Peso filipino','Philippine peso')");
db.execSQL("INSERT INTO divisas VALUES('PKR','586','Rupia pakistaní','Pakistani rupee')");
db.execSQL("INSERT INTO divisas VALUES('PLN','985','Złoty','Polish złoty')");
db.execSQL("INSERT INTO divisas VALUES('PYG','600','Guaraní','Paraguayan guaraní')");
db.execSQL("INSERT INTO divisas VALUES('QAR','634','Riyal qatarí','Qatari riyal')");
db.execSQL("INSERT INTO divisas VALUES('RON','946','Leu rumano','Romanian leu')");
db.execSQL("INSERT INTO divisas VALUES('RSD','941','Dinar serbio','Serbian dinar')");
db.execSQL("INSERT INTO divisas VALUES('RUB','643','Rublo ruso','Russian ruble')");
db.execSQL("INSERT INTO divisas VALUES('RWF','646','Franco ruandés','Rwandan franc')");
db.execSQL("INSERT INTO divisas VALUES('SAR','682','Riyal saudí','Saudi riyal')");
db.execSQL("INSERT INTO divisas VALUES('SBD','90','Dólar de las Islas Salomón','Solomon Islands dollar')");
db.execSQL("INSERT INTO divisas VALUES('SCR','690','Rupia seychelense','Seychelles rupee')");
db.execSQL("INSERT INTO divisas VALUES('SDG','938','Dinar sudanés','Sudanese pound')");
db.execSQL("INSERT INTO divisas VALUES('SEK','752','Corona sueca','Swedish krona/kronor')");
db.execSQL("INSERT INTO divisas VALUES('SGD','702','Dólar de Singapur','Singapore dollar')");
db.execSQL("INSERT INTO divisas VALUES('SHP','654','Libra de Santa Elena','Saint Helena pound')");
db.execSQL("INSERT INTO divisas VALUES('SLL','694','Leone','Sierra Leonean leone')");
db.execSQL("INSERT INTO divisas VALUES('SOS','706','Chelín somalí','Somali shilling')");
db.execSQL("INSERT INTO divisas VALUES('SRD','968','Dólar surinamés','Surinamese dollar')");
db.execSQL("INSERT INTO divisas VALUES('SSP','728','Libra sursudanesa','South Sudanese pound')");
db.execSQL("INSERT INTO divisas VALUES('STD','678','Dobra','São Tomé and Príncipe dobra')");
db.execSQL("INSERT INTO divisas VALUES('SVC','222','Colon Salvadoreño','Salvadoran colón')");
db.execSQL("INSERT INTO divisas VALUES('SYP','760','Libra siria','Syrian pound')");
db.execSQL("INSERT INTO divisas VALUES('SZL','748','Lilangeni','Swazi lilangeni')");
db.execSQL("INSERT INTO divisas VALUES('THB','764','Baht','Thai baht')");
db.execSQL("INSERT INTO divisas VALUES('TJS','972','Somoni tayiko','Tajikistani somoni')");
db.execSQL("INSERT INTO divisas VALUES('TMT','934','Manat turcomano','Turkmenistani manat')");
db.execSQL("INSERT INTO divisas VALUES('TND','788','Dinar tunecino','Tunisian dinar')");
db.execSQL("INSERT INTO divisas VALUES('TOP','776','Paʻanga','Tongan paʻanga')");
db.execSQL("INSERT INTO divisas VALUES('TRY','949','Lira turca','Turkish lira')");
db.execSQL("INSERT INTO divisas VALUES('TTD','780','Dólar de Trinidad y Tobago','Trinidad and Tobago dollar')");
db.execSQL("INSERT INTO divisas VALUES('TWD','901','Nuevo dólar taiwanés','New Taiwan dollar')");
db.execSQL("INSERT INTO divisas VALUES('TZS','834','Chelín tanzano','Tanzanian shilling')");
db.execSQL("INSERT INTO divisas VALUES('UAH','980','Grivna','Ukrainian hryvnia')");
db.execSQL("INSERT INTO divisas VALUES('UGX','800','Chelín ugandés','Ugandan shilling')");
db.execSQL("INSERT INTO divisas VALUES('USD','840','Dólar estadounidense','United States dollar')");
db.execSQL("INSERT INTO divisas VALUES('USN','997','Dólar estadounidense (Siguiente día)','United States dollar (next day) (funds code)')");
db.execSQL("INSERT INTO divisas VALUES('UYI','940','Peso en Unidades Indexadas (Uruguay)','Uruguay Peso en Unidades Indexadas (URUIURUI) (funds code)')");
db.execSQL("INSERT INTO divisas VALUES('UYU','858','Peso uruguayo','Uruguayan peso')");
db.execSQL("INSERT INTO divisas VALUES('UZS','860','Som uzbeko','Uzbekistan som')");
db.execSQL("INSERT INTO divisas VALUES('VEF','937','Bolívar','Venezuelan bolívar')");
db.execSQL("INSERT INTO divisas VALUES('VND','704','Dong vietnamita','Vietnamese dong')");
db.execSQL("INSERT INTO divisas VALUES('VUV','548','Vatu','Vanuatu vatu')");
db.execSQL("INSERT INTO divisas VALUES('WST','882','Tala','Samoan tala')");
db.execSQL("INSERT INTO divisas VALUES('XAF','950','Franco CFA de África Central','CFA franc BEAC')");
db.execSQL("INSERT INTO divisas VALUES('XAG','961','Plata (una onza troy)','Silver (one troy ounce)')");
db.execSQL("INSERT INTO divisas VALUES('XAU','959','Oro (una onza troy)','Gold (one troy ounce)')");
db.execSQL("INSERT INTO divisas VALUES('XBA','955','Unidad compuesta europea (EURCO) (Unidad del mercados de bonos)','European Composite Unit (EURCO) (bond market unit)')");
db.execSQL("INSERT INTO divisas VALUES('XBB','956','Unidad Monetaria europea (E.M.U.-6) (Unidad del mercado de bonos)','European Monetary Unit (E.M.U.-6) (bond market unit)')");
db.execSQL("INSERT INTO divisas VALUES('XBC','957','Unidad europea de cuenta 9 (E.U.A.-9) (Unidad del mercado de bonos)','European Unit of Account 9 (E.U.A.-9) (bond market unit)')");
db.execSQL("INSERT INTO divisas VALUES('XBD','958','Unidad europea de cuenta 17 (E.U.A.-17) (Unidad del mercado de bonos)','European Unit of Account 17 (E.U.A.-17) (bond market unit)')");
db.execSQL("INSERT INTO divisas VALUES('XCD','951','Dólar del Caribe Oriental','East Caribbean dollar')");
db.execSQL("INSERT INTO divisas VALUES('XDR','960','Derechos especiales de giro','Special drawing rights')");
db.execSQL("INSERT INTO divisas VALUES('XOF','952','Franco CFA de África Occidental','CFA franc BCEAO')");
db.execSQL("INSERT INTO divisas VALUES('XPD','964','Paladio (una onza troy)','Palladium (one troy ounce)')");
db.execSQL("INSERT INTO divisas VALUES('XPF','953','Franco CFP','CFP franc (franc Pacifique)')");
db.execSQL("INSERT INTO divisas VALUES('XPT','962','Platino (una onza troy)','Platinum (one troy ounce)')");
db.execSQL("INSERT INTO divisas VALUES('XSU','994','SUCRE','SUCRE')");
db.execSQL("INSERT INTO divisas VALUES('XTS','963','Reservado para pruebas','Code reserved for testing purposes')");
db.execSQL("INSERT INTO divisas VALUES('XUA','965','Unidad de cuenta BAD','ADB Unit of Account')");
db.execSQL("INSERT INTO divisas VALUES('YER','886','Rial yemení','Yemeni rial')");
db.execSQL("INSERT INTO divisas VALUES('ZAR','710','Rand','South African rand')");
db.execSQL("INSERT INTO divisas VALUES('ZMW','967','Kwacha zambiano','Zambian kwacha')");
db.execSQL("INSERT INTO divisas VALUES('ZWL','932','Dólar zimbabuense','Zimbabwean dollar A/10')");
db.execSQL("INSERT INTO divisas VALUES('XXX',NULL,'Sin divisa','No currency ')");
db.execSQL("DROP TABLE IF EXISTS paises");
db.execSQL("CREATE TABLE paises (ID_PAIS  PRIMARY KEY  NOT NULL  UNIQUE , ID_PAIS_2 , PAIS_EN , PAIS_ES , DIVISAS , HIMNO , ICON )");
db.execSQL("INSERT INTO paises VALUES('AF','AFG','Afghanistan','Afganistán','AFN','https://upload.wikimedia.org/wikipedia/commons/b/bd/National_Anthem_of_the_Islamic_Republic_of_Afghanistan.ogg',NULL)");
db.execSQL("INSERT INTO paises VALUES('AX','ALA','Åland Islands','Åland','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('AL','ALB','Albania','Albania','ALL',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('DZ','DZA','Algeria','Argelia','DZD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('AS','ASM','American Samoa','Samoa Americana','USD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('AD','AND','Andorra','Andorra','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('AO','AGO','Angola','Angola','AOA',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('AI','AIA','Anguilla','Anguila','XCD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('AQ','ATA','Antarctica','Antártida','XXX',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('AG','ATG','Antigua and Barbuda','Antigua y Barbuda','XCD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('AR','ARG','Argentina','Argentina','ARS',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('AM','ARM','Armenia','Armenia','AMD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('AW','ABW','Aruba','Aruba','AWG',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('AU','AUS','Australia','Australia','AUD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('AT','AUT','Austria','Austria','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('AZ','AZE','Azerbaijan','Azerbaiyán','AZN',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('BS','BHS','Bahamas','Bahamas','BSD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('BH','BHR','Bahrain','Baréin','BHD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('BD','BGD','Bangladesh','Bangladés','BDT',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('BB','BRB','Barbados','Barbados','BBD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('BY','BLR','Belarus','Bielorrusia','BYR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('BE','BEL','Belgium','Bélgica','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('BZ','BLZ','Belize','Belice','BZD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('BJ','BEN','Benin','Benín','XOF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('BM','BMU','Bermuda','Bermudas','BMD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('BT','BTN','Bhutan','Bután','BTN-INR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('BO','BOL','Bolivia (Plurinational State of)','Bolivia','BOB-BOV',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('BQ','BES','Bonaire, Sint Eustatius and Saba','Bonaire, San Eustaquio y Saba','0',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('BA','BIH','Bosnia and Herzegovina','Bosnia y Herzegovina','BAM',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('BW','BWA','Botswana','Botsuana','BWP',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('BV','BVT','Bouvet Island','Isla Bouvet','NOK',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('BR','BRA','Brazil','Brasil','BRL',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('IO','IOT','British Indian Ocean Territory','Territorio Británico del Océano Índico','USD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('BN','BRN','Brunei Darussalam','Brunéi','BND',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('BG','BGR','Bulgaria','Bulgaria','BGN',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('BF','BFA','Burkina Faso','Burkina Faso','XOF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('BI','BDI','Burundi','Burundi','BIF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('CV','CPV','Cabo Verde','Cabo Verde','CVE',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('KH','KHM','Cambodia','Camboya','KHR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('CM','CMR','Cameroon','Camerún','XAF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('CA','CAN','Canada','Canadá','CAD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('KY','CYM','Cayman Islands','Islas Caimán','KYD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('CF','CAF','Central African Republic','República Centroafricana','XAF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('TD','TCD','Chad','Chad','XAF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('CL','CHL','Chile','Chile','CLF-CLP',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('CN','CHN','China','China','CNY-TWD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('CX','CXR','Christmas Island','Isla de Navidad','AUD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('CC','CCK','Cocos (Keeling) Islands','Islas Cocos','AUD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('CO','COL','Colombia','Colombia','COP-COU',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('KM','COM','Comoros','Comoras','KMF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('CG','COG','Congo, Republic of the','República del Congo','XAF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('CD','COD','Congo (Democratic Republic of the)','República Democrática del Congo','CDF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('CK','COK','Cook Islands','Islas Cook','NZD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('CR','CRI','Costa Rica','Costa Rica','CRC',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('CI','CIV','Côte d''Ivoire','Costa de Marfil','XOF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('HR','HRV','Croatia','Croacia','HRK',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('CU','CUB','Cuba','Cuba','CUC-CUP',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('CW','CUW','Curaçao','Curazao','ANG',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('CY','CYP','Cyprus','Chipre','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('CZ','CZE','Czechia','República Checa','CZK',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('DK','DNK','Denmark','Dinamarca','DKK',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('DJ','DJI','Djibouti','Yibuti','DJF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('DM','DMA','Dominica','Dominica','DOP-XCD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('DO','DOM','Dominican Republic','República Dominicana','DOP',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('EC','ECU','Ecuador','Ecuador','USD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('EG','EGY','Egypt','Egipto','EGP',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('SV','SLV','El Salvador','El Salvador','SVC-USD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('GQ','GNQ','Equatorial Guinea','Guinea Ecuatorial','XAF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('ER','ERI','Eritrea','Eritrea','ERN',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('EE','EST','Estonia','Estonia','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('ET','ETH','Ethiopia','Etiopía','ETB',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('FK','FLK','Falkland Islands (Malvinas)','Islas Malvinas','FKP',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('FO','FRO','Faroe Islands','Islas Feroe','DKK',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('FJ','FJI','Fiji','Fiyi','FJD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('FI','FIN','Finland','Finlandia','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('FR','FRA','France','Francia','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('GF','GUF','French Guiana','Guayana Francesa','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('PF','PYF','French Polynesia','Polinesia Francesa','XPF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('TF','ATF','French Southern Territories','Tierras Australes y Antárticas Francesas','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('GA','GAB','Gabon','Gabón','XAF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('GM','GMB','Gambia','Gambia','GMD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('GE','GEO','Georgia','Georgia','GEL-XXX',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('DE','DEU','Germany','Alemania','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('GH','GHA','Ghana','Ghana','GHS',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('GI','GIB','Gibraltar','Gibraltar','GIP',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('GR','GRC','Greece','Grecia','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('GL','GRL','Greenland','Groenlandia','DKK',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('GD','GRD','Grenada','Granada','XCD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('GP','GLP','Guadeloupe','Guadalupe','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('GU','GUM','Guam','Guam','USD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('GT','GTM','Guatemala','Guatemala','GTQ',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('GG','GGY','Guernsey','Guernsey','GBP',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('GN','GIN','Guinea','Guinea','GNF-PGK-XAF-XOF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('GW','GNB','Guinea-Bissau','Guinea-Bisáu','XOF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('GY','GUY','Guyana','Guyana','GYD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('HT','HTI','Haiti','Haití','HTG-USD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('HM','HMD','Heard Island and McDonald Islands','Islas Heard y McDonald','AUD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('VA','VAT','Holy See','Vaticano, Ciudad del','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('HN','HND','Honduras','Honduras','HNL',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('HK','HKG','Hong Kong','Hong Kong','HKD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('HU','HUN','Hungary','Hungría','HUF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('IS','ISL','Iceland','Islandia','ISK',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('IN','IND','India','India','INR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('ID','IDN','Indonesia','Indonesia','IDR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('IR','IRN','Iran (Islamic Republic of)','Irán','IRR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('IQ','IRQ','Iraq','Irak','IQD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('IE','IRL','Ireland','Irlanda','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('IM','IMN','Isle of Man','Isla de Man','GBP',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('IL','ISR','Israel','Israel','ILS',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('IT','ITA','Italy','Italia','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('JM','JAM','Jamaica','Jamaica','JMD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('JP','JPN','Japan','Japón','JPY',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('JE','JEY','Jersey','Jersey','GBP',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('JO','JOR','Jordan','Jordania','JOD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('KZ','KAZ','Kazakhstan','Kazajistán','KZT',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('KE','KEN','Kenya','Kenia','KES',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('KI','KIR','Kiribati','Kiribati','AUD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('KP','PRK','Korea (Democratic People''s Republic of)','Corea del Norte','KPW',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('KR','KOR','Korea (Republic of)','Corea del Sur','KRW',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('KW','KWT','Kuwait','Kuwait','KWD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('KG','KGZ','Kyrgyzstan','Kirguistán','KGS',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('LA','LAO','Lao People''s Democratic Republic','Laos','LAK',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('LV','LVA','Latvia','Letonia','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('LB','LBN','Lebanon','Líbano','LBP',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('LS','LSO','Lesotho','Lesoto','LSL-ZAR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('LR','LBR','Liberia','Liberia','LRD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('LY','LBY','Libya','Libia','LYD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('LI','LIE','Liechtenstein','Liechtenstein','CHF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('LT','LTU','Lithuania','Lituania','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('LU','LUX','Luxembourg','Luxemburgo','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('MO','MAC','Macao','Macao','MOP',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('MK','MKD','Macedonia (the former Yugoslav Republic of)','Macedonia','MKD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('MG','MDG','Madagascar','Madagascar','MGA',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('MW','MWI','Malawi','Malaui','MWK',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('MY','MYS','Malaysia','Malasia','MYR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('MV','MDV','Maldives','Maldivas','MVR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('ML','MLI','Mali','Malí','XOF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('MT','MLT','Malta','Malta','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('MH','MHL','Marshall Islands','Islas Marshall','USD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('MQ','MTQ','Martinique','Martinica','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('MR','MRT','Mauritania','Mauritania','MRO',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('MU','MUS','Mauritius','Mauricio','MUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('YT','MYT','Mayotte','Mayotte','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('MX','MEX','Mexico','México','MXN-MXV',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('FM','FSM','Micronesia (Federated States of)','Micronesia','USD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('MD','MDA','Moldova (Republic of)','Moldavia','MDL',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('MC','MCO','Monaco','Mónaco','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('MN','MNG','Mongolia','Mongolia','MNT',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('ME','MNE','Montenegro','Montenegro','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('MS','MSR','Montserrat','Montserrat','XCD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('MA','MAR','Morocco','Marruecos','MAD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('MZ','MOZ','Mozambique','Mozambique','MZN',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('MM','MMR','Myanmar','Myanmar','MMK',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('NA','NAM','Namibia','Namibia','NAD-ZAR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('NR','NRU','Nauru','Nauru','AUD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('NP','NPL','Nepal','Nepal','NPR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('NL','NLD','Netherlands','Países Bajos','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('NC','NCL','New Caledonia','Nueva Caledonia','XPF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('NZ','NZL','New Zealand','Nueva Zelanda','NZD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('NI','NIC','Nicaragua','Nicaragua','NIO',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('NE','NER','Niger','Níger','XOF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('NG','NGA','Nigeria','Nigeria','NGN',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('NU','NIU','Niue','Niue','NZD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('NF','NFK','Norfolk Island','Norfolk','AUD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('MP','MNP','Northern Mariana Islands','Islas Marianas del Norte','USD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('NO','NOR','Norway','Noruega','NOK',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('OM','OMN','Oman','Omán','OMR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('PK','PAK','Pakistan','Pakistán','PKR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('PW','PLW','Palau','Palaos','USD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('PS','PSE','Palestine, State of','Palestina','XXX',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('PA','PAN','Panama','Panamá','PAB-USD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('PG','PNG','Papua New Guinea','Papúa Nueva Guinea','PGK',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('PY','PRY','Paraguay','Paraguay','PYG',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('PE','PER','Peru','Perú','PEN',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('PH','PHL','Philippines','Filipinas','PHP',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('PN','PCN','Pitcairn','Islas Pitcairn','NZD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('PL','POL','Poland','Polonia','PLN',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('PT','PRT','Portugal','Portugal','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('PR','PRI','Puerto Rico','Puerto Rico','USD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('QA','QAT','Qatar','Catar','QAR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('RE','REU','Réunion','Reunión','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('RO','ROU','Romania','Rumania','RON',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('RU','RUS','Russian Federation','Rusia','RUB',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('RW','RWA','Rwanda','Ruanda','RWF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('BL','BLM','Saint Barthélemy','San Bartolomé','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('SH','SHN','Saint Helena, Ascension and Tristan da Cunha','Santa Elena, Ascensión y Tristán de Acuña','SHP',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('KN','KNA','Saint Kitts and Nevis','San Cristóbal y Nieves','XCD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('LC','LCA','Saint Lucia','Santa Lucía','XCD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('MF','MAF','Saint Martin (French part)','San Martín','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('PM','SPM','Saint Pierre and Miquelon','San Pedro y Miquelón','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('VC','VCT','Saint Vincent and the Grenadines','San Vicente y las Granadinas','XCD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('WS','WSM','Samoa','Samoa','USD-WST',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('SM','SMR','San Marino','San Marino','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('ST','STP','Sao Tome and Principe','Santo Tomé y Príncipe','STD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('SA','SAU','Saudi Arabia','Arabia Saudita','SAR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('SN','SEN','Senegal','Senegal','XOF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('RS','SRB','Serbia','Serbia','RSD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('SC','SYC','Seychelles','Seychelles','SCR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('SL','SLE','Sierra Leone','Sierra Leona','SLL',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('SG','SGP','Singapore','Singapur','SGD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('SX','SXM','Sint Maarten (Dutch part)','Sint Maarten','ANG',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('SK','SVK','Slovakia','Eslovaquia','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('SI','SVN','Slovenia','Eslovenia','EUR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('SB','SLB','Solomon Islands','Islas Salomón','SBD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('SO','SOM','Somalia','Somalia','SOS',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('ZA','ZAF','South Africa','Sudáfrica','ZAR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('GS','SGS','South Georgia and the South Sandwich Islands','Islas Georgias del Sur y Sandwich del Sur','XXX',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('SS','SSD','South Sudan','Sudán del Sur','SSP',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('ES','ESP','Spain','España','EUR','https://upload.wikimedia.org/wikipedia/commons/c/c8/Marcha_Real-Royal_March_by_US_Navy_Band.ogg','spain.svg')");
db.execSQL("INSERT INTO paises VALUES('LK','LKA','Sri Lanka','Sri Lanka','LKR',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('SD','SDN','Sudan','Sudán','SDG-SSP',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('SR','SUR','Suriname','Surinam','SRD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('SJ','SJM','Svalbard and Jan Mayen','Svalbard y Jan Mayen','NOK',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('SZ','SWZ','Swaziland','Suazilandia','SZL',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('SE','SWE','Sweden','Suecia','SEK',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('CH','CHE','Switzerland','Suiza','CHE-CHF-CHW',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('SY','SYR','Syrian Arab Republic','Siria','SYP',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('TW','TWN','Taiwan, Province of China[a]','Taiwán (República de China)','TWD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('TJ','TJK','Tajikistan','Tayikistán','TJS',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('TZ','TZA','Tanzania, United Republic of','Tanzania','TZS',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('TH','THA','Thailand','Tailandia','THB',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('TL','TLS','Timor-Leste','Timor Oriental','USD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('TG','TGO','Togo','Togo','XOF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('TK','TKL','Tokelau','Tokelau','NZD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('TO','TON','Tonga','Tonga','TOP',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('TT','TTO','Trinidad and Tobago','Trinidad y Tobago','TTD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('TN','TUN','Tunisia','Túnez','TND',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('TR','TUR','Turkey','Turquía','TRY',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('TM','TKM','Turkmenistan','Turkmenistán','TMT',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('TC','TCA','Turks and Caicos Islands','Islas Turcas y Caicos','USD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('TV','TUV','Tuvalu','Tuvalu','AUD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('UG','UGA','Uganda','Uganda','UGX',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('UA','UKR','Ukraine','Ucrania','UAH',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('AE','ARE','United Arab Emirates','Emiratos Árabes Unidos','AED',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('GB','GBR','United Kingdom of Great Britain and Northern Ireland','Reino Unido','GBP',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('US','USA','United States of America','Estados Unidos','USD-USN',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('UM','UMI','United States Minor Outlying Islands','Islas ultramarinas de Estados Unidos','USD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('UY','URY','Uruguay','Uruguay','UYI-UYU',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('UZ','UZB','Uzbekistan','Uzbekistán','UZS',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('VU','VUT','Vanuatu','Vanuatu','VUV',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('VE','VEN','Venezuela (Bolivarian Republic of)','Venezuela','VEF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('VN','VNM','Viet Nam','Vietnam','VND',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('VG','VGB','Virgin Islands (British)','Islas Vírgenes Británicas','USD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('VI','VIR','Virgin Islands (U.S.)','Islas Vírgenes de los Estados Unidos','USD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('WF','WLF','Wallis and Futuna','Wallis y Futuna','XPF',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('EH','ESH','Western Sahara','República Árabe Saharaui Democrática','MAD',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('YE','YEM','Yemen','Yemen','YER',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('ZM','ZMB','Zambia','Zambia','ZMW',NULL,NULL)");
db.execSQL("INSERT INTO paises VALUES('ZW','ZWE','Zimbabwe','Zimbabue','ZWL',NULL,NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // En caso de una nueva versión habría que actualizar las tablas
    }

    /*@Override
    public void guardarPuntuacion(int puntos, String nombre, long fecha) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("INSERT INTO puntuaciones VALUES ( null, " + puntos + ", '" + nombre + "', " + fecha + ")");
            db.close();
        } catch (SQLiteException es) {
            Toast.makeText(context, "No se puede guardar puntuaciones.Debe eliminar la base de datos actual manualmente ya que está en la versión 2", Toast.LENGTH_SHORT).show();
        }
    }*/

    public Vector<Pais> listarPaises() {
        Vector<Pais> result = new Vector<Pais>();
        try {
            SQLiteDatabase db = getReadableDatabase();
            /// Cursor cursor = db.rawQuery("SELECT puntos, nombre, fecha FROM " + "puntuaciones ORDER BY puntos DESC LIMIT " + cantidad, null);

            // REEMPLAZAR RAWQUERY POR QUERY
            //String[] CAMPOS = {"ID_DIVISA", "ID_DIVISA2" , "DIVISA_ES" , "DIVISA_EN", "HIMNO", "ICONO"};
            String[] CAMPOS = {"ID_PAIS",  "ID_PAIS_2" , "PAIS_EN" , "PAIS_ES" , "DIVISAS" , "HIMNO" , "ICON"};
            Cursor cursor = db.query("paises", CAMPOS, null, null, null, null, null, null);

            while (cursor.moveToNext()) {
                result.add(new Pais(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4),cursor.getString(5),cursor.getString(6)));
            }
            cursor.close();
            db.close();
        } catch (SQLiteException es) {
            Toast.makeText(context, "Error a lisar los paises", Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    public Vector<Divisa> listarDivisas() {
        Vector<Divisa> result = new Vector<Divisa>();
        try {
            SQLiteDatabase db = getReadableDatabase();
            /// Cursor cursor = db.rawQuery("SELECT puntos, nombre, fecha FROM " + "puntuaciones ORDER BY puntos DESC LIMIT " + cantidad, null);

            // REEMPLAZAR RAWQUERY POR QUERY
            String[] CAMPOS = {"ID_DIVISA", "ID_DIVISA2" , "DIVISA_ES" , "DIVISA_EN"};

            Cursor cursor = db.query("divisas", CAMPOS, null, null, null, null, null, null);

            while (cursor.moveToNext()) {
                result.add(new Divisa(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            }
            cursor.close();
            db.close();
        } catch (SQLiteException es) {
            Toast.makeText(context, "Error a lisar los divisas", Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    public Pais getPais(String idPais) {

       Pais pais=null;

        try {
            SQLiteDatabase db = getReadableDatabase();
            /// Cursor cursor = db.rawQuery("SELECT puntos, nombre, fecha FROM " + "puntuaciones ORDER BY puntos DESC LIMIT " + cantidad, null);

            // REEMPLAZAR RAWQUERY POR QUERY
            //String[] CAMPOS = {"ID_DIVISA", "ID_DIVISA2" , "DIVISA_ES" , "DIVISA_EN", "HIMNO", "ICONO"};
            String[] CAMPOS = {"ID_PAIS",  "ID_PAIS_2" , "PAIS_EN" , "PAIS_ES" , "DIVISAS" , "HIMNO" , "ICON"};
            Cursor cursor = db.query("paises", CAMPOS, "ID_PAIS=?", new String[]{idPais}, null, null, null, null);

            while (cursor.moveToNext()) {
                pais = new Pais(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4),cursor.getString(5),cursor.getString(6));
            }
            cursor.close();
            db.close();
        } catch (SQLiteException es) {
            Toast.makeText(context, "Error a lisar los paises", Toast.LENGTH_SHORT).show();
        }
        return pais;
    }
}
