package com.sojava.beehive.framework.component.inpatienthomepage.bean;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the inpatient_homepage_analy database table.
 * 
 */
@Entity
@Table(name="inpatient_homepage_analy", schema="data_transform")
@NamedQuery(name="InpatientHomepageAnaly.findAll", query="SELECT i FROM InpatientHomepageAnaly i")
public class InpatientHomepageAnaly implements Serializable, Cloneable {
	private static final long serialVersionUID = 910544509233282386L;

	@EmbeddedId
	private InpatientHomepageAnalyPK id;

	private Integer checked;

	private String flag;

	private String bah;

	private String bazl;

	private Double bdblzpf;

	private String blh;

	private String blzd;

	private Double blzdf;

	private String bmy;

	private Double bzlzf;

	private String bzsh;

	private Double bzss;

	@Column(name="bzyzs_nl")
	private String bzyzsNl;

	private String csd;

	private String csrq;

	private String cybf;

	private String cykb;

	private String cysj;

	@Column(name="cysj_s")
	private String cysjS;

	private String dh;

	private String dh1;

	private String dwdh;

	private String dz;

	private String ez1;

	private String ez10;

	private String ez11;

	private String ez12;

	private String ez13;

	private String ez14;

	private String ez15;

	private String ez16;

	private String ez17;

	private String ez18;

	private String ez19;

	private String ez2;

	private String ez20;

	private String ez21;

	private String ez22;

	private String ez23;

	private String ez24;

	private String ez25;

	private String ez26;

	private String ez27;

	private String ez28;

	private String ez29;

	private String ez3;

	private String ez30;

	private String ez31;

	private String ez32;

	private String ez33;

	private String ez34;

	private String ez35;

	private String ez36;

	private String ez37;

	private String ez38;

	private String ez39;

	private String ez4;

	private String ez40;

	private String ez41;

	private String ez5;

	private String ez6;

	private String ez7;

	private String ez8;

	private String ez9;

	private Double fsszlxmf;

	private String gg;

	private String gj;

	private String gmyw;

	private String gx;

	private String gzdwjdz;

	private String hkdz;

	private Double hlf;

	private String hy;

	private String jbbm;

	private String jbbm1;

	private String jbbm2;

	private String jbdm;

	private Double jcyyclf;

	private String jgmc;

	private String jkkh;

	private String jxys;

	private Double kff;

	private Double kjywf;

	private String kzr;

	private Double lczdxmf;

	private String lxrxm;

	private String lyfs;

	private String md;

	private String mz;

	@Column(name="mzd_zyzd")
	private String mzdZyzd;

	private Double mzf;

	private String mzfs1;

	private String mzfs10;

	private String mzfs11;

	private String mzfs12;

	private String mzfs13;

	private String mzfs14;

	private String mzfs15;

	private String mzfs16;

	private String mzfs17;

	private String mzfs18;

	private String mzfs19;

	private String mzfs2;

	private String mzfs20;

	private String mzfs21;

	private String mzfs22;

	private String mzfs23;

	private String mzfs24;

	private String mzfs25;

	private String mzfs26;

	private String mzfs27;

	private String mzfs28;

	private String mzfs29;

	private String mzfs3;

	private String mzfs30;

	private String mzfs31;

	private String mzfs32;

	private String mzfs33;

	private String mzfs34;

	private String mzfs35;

	private String mzfs36;

	private String mzfs37;

	private String mzfs38;

	private String mzfs39;

	private String mzfs4;

	private String mzfs40;

	private String mzfs41;

	private String mzfs5;

	private String mzfs6;

	private String mzfs7;

	private String mzfs8;

	private String mzfs9;

	private String mzys1;

	private String mzys10;

	private String mzys11;

	private String mzys12;

	private String mzys13;

	private String mzys14;

	private String mzys15;

	private String mzys16;

	private String mzys17;

	private String mzys18;

	private String mzys19;

	private String mzys2;

	private String mzys20;

	private String mzys21;

	private String mzys22;

	private String mzys23;

	private String mzys24;

	private String mzys25;

	private String mzys26;

	private String mzys27;

	private String mzys28;

	private String mzys29;

	private String mzys3;

	private String mzys30;

	private String mzys31;

	private String mzys32;

	private String mzys33;

	private String mzys34;

	private String mzys35;

	private String mzys36;

	private String mzys37;

	private String mzys38;

	private String mzys39;

	private String mzys4;

	private String mzys40;

	private String mzys41;

	private String mzys5;

	private String mzys6;

	private String mzys7;

	private String mzys8;

	private String mzys9;

	@Column(name="mzzd_xyzd")
	private String mzzdXyzd;

	private String nl;

	private Double nxyzlzpf;

	private Double qdblzpf;

	private String qkdj1;

	private String qkdj10;

	private String qkdj11;

	private String qkdj12;

	private String qkdj13;

	private String qkdj14;

	private String qkdj15;

	private String qkdj16;

	private String qkdj17;

	private String qkdj18;

	private String qkdj19;

	private String qkdj2;

	private String qkdj20;

	private String qkdj21;

	private String qkdj22;

	private String qkdj23;

	private String qkdj24;

	private String qkdj25;

	private String qkdj26;

	private String qkdj27;

	private String qkdj28;

	private String qkdj29;

	private String qkdj3;

	private String qkdj30;

	private String qkdj31;

	private String qkdj32;

	private String qkdj33;

	private String qkdj34;

	private String qkdj35;

	private String qkdj36;

	private String qkdj37;

	private String qkdj38;

	private String qkdj39;

	private String qkdj4;

	private String qkdj40;

	private String qkdj41;

	private String qkdj5;

	private String qkdj6;

	private String qkdj7;

	private String qkdj8;

	private String qkdj9;

	private String qkylb1;

	private String qkylb10;

	private String qkylb11;

	private String qkylb12;

	private String qkylb13;

	private String qkylb14;

	private String qkylb15;

	private String qkylb16;

	private String qkylb17;

	private String qkylb18;

	private String qkylb19;

	private String qkylb2;

	private String qkylb20;

	private String qkylb21;

	private String qkylb22;

	private String qkylb23;

	private String qkylb24;

	private String qkylb25;

	private String qkylb26;

	private String qkylb27;

	private String qkylb28;

	private String qkylb29;

	private String qkylb3;

	private String qkylb30;

	private String qkylb31;

	private String qkylb32;

	private String qkylb33;

	private String qkylb34;

	private String qkylb35;

	private String qkylb36;

	private String qkylb37;

	private String qkylb38;

	private String qkylb39;

	private String qkylb4;

	private String qkylb40;

	private String qkylb41;

	private String qkylb5;

	private String qkylb6;

	private String qkylb7;

	private String qkylb8;

	private String qkylb9;

	private Double qtf;

	private Double qtfy;

	private String qtzd1;

	private String qtzd10;

	private String qtzd11;

	private String qtzd12;

	private String qtzd13;

	private String qtzd14;

	private String qtzd15;

	private String qtzd16;

	private String qtzd17;

	private String qtzd18;

	private String qtzd19;

	private String qtzd2;

	private String qtzd20;

	private String qtzd21;

	private String qtzd22;

	private String qtzd23;

	private String qtzd24;

	private String qtzd25;

	private String qtzd26;

	private String qtzd27;

	private String qtzd28;

	private String qtzd29;

	private String qtzd3;

	private String qtzd30;

	private String qtzd31;

	private String qtzd32;

	private String qtzd33;

	private String qtzd34;

	private String qtzd35;

	private String qtzd36;

	private String qtzd37;

	private String qtzd38;

	private String qtzd39;

	private String qtzd4;

	private String qtzd40;

	private String qtzd5;

	private String qtzd6;

	private String qtzd7;

	private String qtzd8;

	private String qtzd9;

	private String rh;

	private String rybf;

	private String rybq1;

	private String rybq10;

	private String rybq11;

	private String rybq12;

	private String rybq13;

	private String rybq14;

	private String rybq15;

	private String rybq16;

	private String rybq17;

	private String rybq18;

	private String rybq19;

	private String rybq2;

	private String rybq20;

	private String rybq21;

	private String rybq22;

	private String rybq23;

	private String rybq24;

	private String rybq25;

	private String rybq26;

	private String rybq27;

	private String rybq28;

	private String rybq29;

	private String rybq3;

	private String rybq30;

	private String rybq31;

	private String rybq32;

	private String rybq33;

	private String rybq34;

	private String rybq35;

	private String rybq36;

	private String rybq37;

	private String rybq38;

	private String rybq39;

	private String rybq4;

	private String rybq40;

	private String rybq5;

	private String rybq6;

	private String rybq7;

	private String rybq8;

	private String rybq9;

	@Column(name="ryh_fz")
	private String ryhFz;

	@Column(name="ryh_t")
	private String ryhT;

	@Column(name="ryh_xs")
	private String ryhXs;

	private String rykb;

	@Column(name="ryq_fz")
	private String ryqFz;

	@Column(name="ryq_t")
	private String ryqT;

	@Column(name="ryq_xs")
	private String ryqXs;

	private String rysj;

	@Column(name="rysj_s")
	private String rysjS;

	private String rytj;

	private String sfzh;

	private String shjb1;

	private String shjb10;

	private String shjb11;

	private String shjb12;

	private String shjb13;

	private String shjb14;

	private String shjb15;

	private String shjb16;

	private String shjb17;

	private String shjb18;

	private String shjb19;

	private String shjb2;

	private String shjb20;

	private String shjb21;

	private String shjb22;

	private String shjb23;

	private String shjb24;

	private String shjb25;

	private String shjb26;

	private String shjb27;

	private String shjb28;

	private String shjb29;

	private String shjb3;

	private String shjb30;

	private String shjb31;

	private String shjb32;

	private String shjb33;

	private String shjb34;

	private String shjb35;

	private String shjb36;

	private String shjb37;

	private String shjb38;

	private String shjb39;

	private String shjb4;

	private String shjb40;

	private String shjb41;

	private String shjb5;

	private String shjb6;

	private String shjb7;

	private String shjb8;

	private String shjb9;

	private String sj;

	private String sjzy;

	private Double ssf;

	private String ssjczbm1;

	private String ssjczbm10;

	private String ssjczbm11;

	private String ssjczbm12;

	private String ssjczbm13;

	private String ssjczbm14;

	private String ssjczbm15;

	private String ssjczbm16;

	private String ssjczbm17;

	private String ssjczbm18;

	private String ssjczbm19;

	private String ssjczbm2;

	private String ssjczbm20;

	private String ssjczbm21;

	private String ssjczbm22;

	private String ssjczbm23;

	private String ssjczbm24;

	private String ssjczbm25;

	private String ssjczbm26;

	private String ssjczbm27;

	private String ssjczbm28;

	private String ssjczbm29;

	private String ssjczbm3;

	private String ssjczbm30;

	private String ssjczbm31;

	private String ssjczbm32;

	private String ssjczbm33;

	private String ssjczbm34;

	private String ssjczbm35;

	private String ssjczbm36;

	private String ssjczbm37;

	private String ssjczbm38;

	private String ssjczbm39;

	private String ssjczbm4;

	private String ssjczbm40;

	private String ssjczbm41;

	private String ssjczbm5;

	private String ssjczbm6;

	private String ssjczbm7;

	private String ssjczbm8;

	private String ssjczbm9;

	private String ssjczmc1;

	private String ssjczmc10;

	private String ssjczmc11;

	private String ssjczmc12;

	private String ssjczmc13;

	private String ssjczmc14;

	private String ssjczmc15;

	private String ssjczmc16;

	private String ssjczmc17;

	private String ssjczmc18;

	private String ssjczmc19;

	private String ssjczmc2;

	private String ssjczmc20;

	private String ssjczmc21;

	private String ssjczmc22;

	private String ssjczmc23;

	private String ssjczmc24;

	private String ssjczmc25;

	private String ssjczmc26;

	private String ssjczmc27;

	private String ssjczmc28;

	private String ssjczmc29;

	private String ssjczmc3;

	private String ssjczmc30;

	private String ssjczmc31;

	private String ssjczmc32;

	private String ssjczmc33;

	private String ssjczmc34;

	private String ssjczmc35;

	private String ssjczmc36;

	private String ssjczmc37;

	private String ssjczmc38;

	private String ssjczmc39;

	private String ssjczmc4;

	private String ssjczmc40;

	private String ssjczmc41;

	private String ssjczmc5;

	private String ssjczmc6;

	private String ssjczmc7;

	private String ssjczmc8;

	private String ssjczmc9;

	private String ssjczrq1;

	private String ssjczrq10;

	private String ssjczrq11;

	private String ssjczrq12;

	private String ssjczrq13;

	private String ssjczrq14;

	private String ssjczrq15;

	private String ssjczrq16;

	private String ssjczrq17;

	private String ssjczrq18;

	private String ssjczrq19;

	private String ssjczrq2;

	private String ssjczrq20;

	private String ssjczrq21;

	private String ssjczrq22;

	private String ssjczrq23;

	private String ssjczrq24;

	private String ssjczrq25;

	private String ssjczrq26;

	private String ssjczrq27;

	private String ssjczrq28;

	private String ssjczrq29;

	private String ssjczrq3;

	private String ssjczrq30;

	private String ssjczrq31;

	private String ssjczrq32;

	private String ssjczrq33;

	private String ssjczrq34;

	private String ssjczrq35;

	private String ssjczrq36;

	private String ssjczrq37;

	private String ssjczrq38;

	private String ssjczrq39;

	private String ssjczrq4;

	private String ssjczrq40;

	private String ssjczrq41;

	private String ssjczrq5;

	private String ssjczrq6;

	private String ssjczrq7;

	private String ssjczrq8;

	private String ssjczrq9;

	private String sslclj;

	private Double ssycxclf;

	private Double sszlf;

	private String sxys;

	private String sz1;

	private String sz10;

	private String sz11;

	private String sz12;

	private String sz13;

	private String sz14;

	private String sz15;

	private String sz16;

	private String sz17;

	private String sz18;

	private String sz19;

	private String sz2;

	private String sz20;

	private String sz21;

	private String sz22;

	private String sz23;

	private String sz24;

	private String sz25;

	private String sz26;

	private String sz27;

	private String sz28;

	private String sz29;

	private String sz3;

	private String sz30;

	private String sz31;

	private String sz32;

	private String sz33;

	private String sz34;

	private String sz35;

	private String sz36;

	private String sz37;

	private String sz38;

	private String sz39;

	private String sz4;

	private String sz40;

	private String sz41;

	private String sz5;

	private String sz6;

	private String sz7;

	private String sz8;

	private String sz9;

	private String username;

	private String wbyy;

	@Column(name="wsy_jgmc")
	private String wsyJgmc;

	private String xb;

	private Double xbyzlzpf;

	private Double xf;

	private String xm;

	private String xserytz;

	private String xsetz;

	private String xsetz2;

	private String xsetz3;

	private String xsetz4;

	private String xsetz5;

	private String xx;

	@Column(name="xy_rybq")
	private String xyRybq;

	private Double xyf;

	private String xzz;

	private String yb1;

	private String yb2;

	private String yb3;

	private String ylfkfs;

	private Double ylfwf;

	private String ywgm;

	private Double yxxzdf;

	private Double yyclf;

	private String yz1;

	private String yz10;

	private String yz11;

	private String yz12;

	private String yz13;

	private String yz14;

	private String yz15;

	private String yz16;

	private String yz17;

	private String yz18;

	private String yz19;

	private String yz2;

	private String yz20;

	private String yz21;

	private String yz22;

	private String yz23;

	private String yz24;

	private String yz25;

	private String yz26;

	private String yz27;

	private String yz28;

	private String yz29;

	private String yz3;

	private String yz30;

	private String yz31;

	private String yz32;

	private String yz33;

	private String yz34;

	private String yz35;

	private String yz36;

	private String yz37;

	private String yz38;

	private String yz39;

	private String yz4;

	private String yz40;

	private String yz41;

	private String yz5;

	private String yz6;

	private String yz7;

	private String yz8;

	private String yz9;

	@Column(name="yzzy_jgmc")
	private String yzzyJgmc;

	private String zb;

	@Column(name="zb_jbbm")
	private String zbJbbm;

	@Column(name="zb_rybq")
	private String zbRybq;

	private Double zcyf;

	private Double zcyf1;

	private Double zcyjf;

	private Double zdf;

	private Double zfje;

	private Double zfy;

	private String zkhs;

	private String zkkb;

	private String zkrq;

	private String zkys;

	private Double zlczf;

	private Double zlf;

	private String zllb;

	private String zrhs;

	private String zrys;

	private String zy;

	private Double zyblzhzf;

	private String zycs;

	private Double zygczl;

	private Double zygs;

	@Column(name="zyl_zyzd")
	private Double zylZyzd;

	private Double zyqt;

	private Double zytnzl;

	private Double zytstpjg;

	private Double zytszl;

	private Double zywz;

	private String zyyj;

	private String zyys;

	private String zyzd;

	@Column(name="zyzd_jbbm")
	private String zyzdJbbm;

	@Column(name="zyzd_jbbm1")
	private String zyzdJbbm1;

	@Column(name="zyzd_jbbm10")
	private String zyzdJbbm10;

	@Column(name="zyzd_jbbm11")
	private String zyzdJbbm11;

	@Column(name="zyzd_jbbm12")
	private String zyzdJbbm12;

	@Column(name="zyzd_jbbm13")
	private String zyzdJbbm13;

	@Column(name="zyzd_jbbm14")
	private String zyzdJbbm14;

	@Column(name="zyzd_jbbm15")
	private String zyzdJbbm15;

	@Column(name="zyzd_jbbm16")
	private String zyzdJbbm16;

	@Column(name="zyzd_jbbm17")
	private String zyzdJbbm17;

	@Column(name="zyzd_jbbm18")
	private String zyzdJbbm18;

	@Column(name="zyzd_jbbm19")
	private String zyzdJbbm19;

	@Column(name="zyzd_jbbm2")
	private String zyzdJbbm2;

	@Column(name="zyzd_jbbm20")
	private String zyzdJbbm20;

	@Column(name="zyzd_jbbm21")
	private String zyzdJbbm21;

	@Column(name="zyzd_jbbm22")
	private String zyzdJbbm22;

	@Column(name="zyzd_jbbm23")
	private String zyzdJbbm23;

	@Column(name="zyzd_jbbm24")
	private String zyzdJbbm24;

	@Column(name="zyzd_jbbm25")
	private String zyzdJbbm25;

	@Column(name="zyzd_jbbm26")
	private String zyzdJbbm26;

	@Column(name="zyzd_jbbm27")
	private String zyzdJbbm27;

	@Column(name="zyzd_jbbm28")
	private String zyzdJbbm28;

	@Column(name="zyzd_jbbm29")
	private String zyzdJbbm29;

	@Column(name="zyzd_jbbm3")
	private String zyzdJbbm3;

	@Column(name="zyzd_jbbm30")
	private String zyzdJbbm30;

	@Column(name="zyzd_jbbm31")
	private String zyzdJbbm31;

	@Column(name="zyzd_jbbm32")
	private String zyzdJbbm32;

	@Column(name="zyzd_jbbm33")
	private String zyzdJbbm33;

	@Column(name="zyzd_jbbm34")
	private String zyzdJbbm34;

	@Column(name="zyzd_jbbm35")
	private String zyzdJbbm35;

	@Column(name="zyzd_jbbm36")
	private String zyzdJbbm36;

	@Column(name="zyzd_jbbm37")
	private String zyzdJbbm37;

	@Column(name="zyzd_jbbm38")
	private String zyzdJbbm38;

	@Column(name="zyzd_jbbm39")
	private String zyzdJbbm39;

	@Column(name="zyzd_jbbm4")
	private String zyzdJbbm4;

	@Column(name="zyzd_jbbm40")
	private String zyzdJbbm40;

	@Column(name="zyzd_jbbm5")
	private String zyzdJbbm5;

	@Column(name="zyzd_jbbm6")
	private String zyzdJbbm6;

	@Column(name="zyzd_jbbm7")
	private String zyzdJbbm7;

	@Column(name="zyzd_jbbm8")
	private String zyzdJbbm8;

	@Column(name="zyzd_jbbm9")
	private String zyzdJbbm9;

	private Double zyzjf;

	private Double zyzl;

	private String zyzljs;

	private String zyzlsb;

	@Column(name="zz_jbbm1")
	private String zzJbbm1;

	@Column(name="zz_jbbm2")
	private String zzJbbm2;

	@Column(name="zz_jbbm3")
	private String zzJbbm3;

	@Column(name="zz_jbbm4")
	private String zzJbbm4;

	@Column(name="zz_jbbm5")
	private String zzJbbm5;

	@Column(name="zz_jbbm6")
	private String zzJbbm6;

	@Column(name="zz_jbbm7")
	private String zzJbbm7;

	@Column(name="zz_rybq1")
	private String zzRybq1;

	@Column(name="zz_rybq2")
	private String zzRybq2;

	@Column(name="zz_rybq3")
	private String zzRybq3;

	@Column(name="zz_rybq4")
	private String zzRybq4;

	@Column(name="zz_rybq5")
	private String zzRybq5;

	@Column(name="zz_rybq6")
	private String zzRybq6;

	@Column(name="zz_rybq7")
	private String zzRybq7;

	private String zz1;

	private String zz2;

	private String zz3;

	private String zz4;

	private String zz5;

	private String zz6;

	private String zz7;

	private String zzjgdm;

	private String zzyjh;

	private String zzys;

	@Transient
	private List<InpatientHomepageAnalyCheck> inpatientHomepageAnalyChecks;

	public List<InpatientHomepageAnalyCheck> getInpatientHomepageAnalyChecks() {
		return inpatientHomepageAnalyChecks;
	}

	public void setInpatientHomepageAnalyChecks(List<InpatientHomepageAnalyCheck> inpatientHomepageAnalyChecks) {
		this.inpatientHomepageAnalyChecks = inpatientHomepageAnalyChecks;
	}

	public InpatientHomepageAnaly() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object clone() throws CloneNotSupportedException {
		InpatientHomepageAnaly self = (InpatientHomepageAnaly) super.clone();
		self.setInpatientHomepageAnalyChecks((List<InpatientHomepageAnalyCheck>) ((ArrayList<InpatientHomepageAnalyCheck>) this.getInpatientHomepageAnalyChecks()).clone());

		return self;
	}

	public InpatientHomepageAnaly(int id, String kind, String type, float version) {
		this.id = new InpatientHomepageAnalyPK(id, kind, type, version);
	}

	public InpatientHomepageAnalyPK getId() {
		return this.id;
	}

	public void setId(InpatientHomepageAnalyPK id) {
		this.id = id;
	}

	public Integer getChecked() {
		return this.checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getBah() {
		return this.bah;
	}

	public void setBah(String bah) {
		this.bah = bah;
	}

	public String getBazl() {
		return this.bazl;
	}

	public void setBazl(String bazl) {
		this.bazl = bazl;
	}

	public Double getBdblzpf() {
		return this.bdblzpf == null ? 0 : this.bdblzpf;
	}

	public void setBdblzpf(Double bdblzpf) {
		this.bdblzpf = bdblzpf;
	}

	public String getBlh() {
		return this.blh;
	}

	public void setBlh(String blh) {
		this.blh = blh;
	}

	public String getBlzd() {
		return this.blzd;
	}

	public void setBlzd(String blzd) {
		this.blzd = blzd;
	}

	public Double getBlzdf() {
		return this.blzdf == null ? 0 : this.blzdf;
	}

	public void setBlzdf(Double blzdf) {
		this.blzdf = blzdf;
	}

	public String getBmy() {
		return this.bmy;
	}

	public void setBmy(String bmy) {
		this.bmy = bmy;
	}

	public Double getBzlzf() {
		return this.bzlzf == null ? 0 : this.bzlzf;
	}

	public void setBzlzf(Double bzlzf) {
		this.bzlzf = bzlzf;
	}

	public String getBzsh() {
		return this.bzsh;
	}

	public void setBzsh(String bzsh) {
		this.bzsh = bzsh;
	}

	public Double getBzss() {
		return this.bzss == null ? 0 : this.bzss;
	}

	public void setBzss(Double bzss) {
		this.bzss = bzss;
	}

	@Column(name="bzyzs_nl")
	public String getBzyzsNl() {
		return this.bzyzsNl;
	}

	@Column(name="bzyzs_nl")
	public void setBzyzsNl(String bzyzsNl) {
		this.bzyzsNl = bzyzsNl;
	}

	public String getCsd() {
		return this.csd;
	}

	public void setCsd(String csd) {
		this.csd = csd;
	}

	public String getCsrq() {
		return this.csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public String getCybf() {
		return this.cybf;
	}

	public void setCybf(String cybf) {
		this.cybf = cybf;
	}

	public String getCykb() {
		return this.cykb;
	}

	public void setCykb(String cykb) {
		this.cykb = cykb;
	}

	public String getCysj() {
		return this.cysj;
	}

	public void setCysj(String cysj) {
		this.cysj = cysj;
	}

	@Column(name="cysj_s")
	public String getCysjS() {
		return this.cysjS;
	}

	@Column(name="cysj_s")
	public void setCysjS(String cysjS) {
		this.cysjS = cysjS;
	}

	public String getDh() {
		return this.dh;
	}

	public void setDh(String dh) {
		this.dh = dh;
	}

	public String getDh1() {
		return this.dh1;
	}

	public void setDh1(String dh1) {
		this.dh1 = dh1;
	}

	public String getDwdh() {
		return this.dwdh;
	}

	public void setDwdh(String dwdh) {
		this.dwdh = dwdh;
	}

	public String getDz() {
		return this.dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
	}

	public String getEz1() {
		return this.ez1;
	}

	public void setEz1(String ez1) {
		this.ez1 = ez1;
	}

	public String getEz10() {
		return this.ez10;
	}

	public void setEz10(String ez10) {
		this.ez10 = ez10;
	}

	public String getEz11() {
		return this.ez11;
	}

	public void setEz11(String ez11) {
		this.ez11 = ez11;
	}

	public String getEz12() {
		return this.ez12;
	}

	public void setEz12(String ez12) {
		this.ez12 = ez12;
	}

	public String getEz13() {
		return this.ez13;
	}

	public void setEz13(String ez13) {
		this.ez13 = ez13;
	}

	public String getEz14() {
		return this.ez14;
	}

	public void setEz14(String ez14) {
		this.ez14 = ez14;
	}

	public String getEz15() {
		return this.ez15;
	}

	public void setEz15(String ez15) {
		this.ez15 = ez15;
	}

	public String getEz16() {
		return this.ez16;
	}

	public void setEz16(String ez16) {
		this.ez16 = ez16;
	}

	public String getEz17() {
		return this.ez17;
	}

	public void setEz17(String ez17) {
		this.ez17 = ez17;
	}

	public String getEz18() {
		return this.ez18;
	}

	public void setEz18(String ez18) {
		this.ez18 = ez18;
	}

	public String getEz19() {
		return this.ez19;
	}

	public void setEz19(String ez19) {
		this.ez19 = ez19;
	}

	public String getEz2() {
		return this.ez2;
	}

	public void setEz2(String ez2) {
		this.ez2 = ez2;
	}

	public String getEz20() {
		return this.ez20;
	}

	public void setEz20(String ez20) {
		this.ez20 = ez20;
	}

	public String getEz21() {
		return this.ez21;
	}

	public void setEz21(String ez21) {
		this.ez21 = ez21;
	}

	public String getEz22() {
		return this.ez22;
	}

	public void setEz22(String ez22) {
		this.ez22 = ez22;
	}

	public String getEz23() {
		return this.ez23;
	}

	public void setEz23(String ez23) {
		this.ez23 = ez23;
	}

	public String getEz24() {
		return this.ez24;
	}

	public void setEz24(String ez24) {
		this.ez24 = ez24;
	}

	public String getEz25() {
		return this.ez25;
	}

	public void setEz25(String ez25) {
		this.ez25 = ez25;
	}

	public String getEz26() {
		return this.ez26;
	}

	public void setEz26(String ez26) {
		this.ez26 = ez26;
	}

	public String getEz27() {
		return this.ez27;
	}

	public void setEz27(String ez27) {
		this.ez27 = ez27;
	}

	public String getEz28() {
		return this.ez28;
	}

	public void setEz28(String ez28) {
		this.ez28 = ez28;
	}

	public String getEz29() {
		return this.ez29;
	}

	public void setEz29(String ez29) {
		this.ez29 = ez29;
	}

	public String getEz3() {
		return this.ez3;
	}

	public void setEz3(String ez3) {
		this.ez3 = ez3;
	}

	public String getEz30() {
		return this.ez30;
	}

	public void setEz30(String ez30) {
		this.ez30 = ez30;
	}

	public String getEz31() {
		return this.ez31;
	}

	public void setEz31(String ez31) {
		this.ez31 = ez31;
	}

	public String getEz32() {
		return this.ez32;
	}

	public void setEz32(String ez32) {
		this.ez32 = ez32;
	}

	public String getEz33() {
		return this.ez33;
	}

	public void setEz33(String ez33) {
		this.ez33 = ez33;
	}

	public String getEz34() {
		return this.ez34;
	}

	public void setEz34(String ez34) {
		this.ez34 = ez34;
	}

	public String getEz35() {
		return this.ez35;
	}

	public void setEz35(String ez35) {
		this.ez35 = ez35;
	}

	public String getEz36() {
		return this.ez36;
	}

	public void setEz36(String ez36) {
		this.ez36 = ez36;
	}

	public String getEz37() {
		return this.ez37;
	}

	public void setEz37(String ez37) {
		this.ez37 = ez37;
	}

	public String getEz38() {
		return this.ez38;
	}

	public void setEz38(String ez38) {
		this.ez38 = ez38;
	}

	public String getEz39() {
		return this.ez39;
	}

	public void setEz39(String ez39) {
		this.ez39 = ez39;
	}

	public String getEz4() {
		return this.ez4;
	}

	public void setEz4(String ez4) {
		this.ez4 = ez4;
	}

	public String getEz40() {
		return this.ez40;
	}

	public void setEz40(String ez40) {
		this.ez40 = ez40;
	}

	public String getEz41() {
		return this.ez41;
	}

	public void setEz41(String ez41) {
		this.ez41 = ez41;
	}

	public String getEz5() {
		return this.ez5;
	}

	public void setEz5(String ez5) {
		this.ez5 = ez5;
	}

	public String getEz6() {
		return this.ez6;
	}

	public void setEz6(String ez6) {
		this.ez6 = ez6;
	}

	public String getEz7() {
		return this.ez7;
	}

	public void setEz7(String ez7) {
		this.ez7 = ez7;
	}

	public String getEz8() {
		return this.ez8;
	}

	public void setEz8(String ez8) {
		this.ez8 = ez8;
	}

	public String getEz9() {
		return this.ez9;
	}

	public void setEz9(String ez9) {
		this.ez9 = ez9;
	}

	public Double getFsszlxmf() {
		return this.fsszlxmf == null ? 0 : this.fsszlxmf;
	}

	public void setFsszlxmf(Double fsszlxmf) {
		this.fsszlxmf = fsszlxmf;
	}

	public String getGg() {
		return this.gg;
	}

	public void setGg(String gg) {
		this.gg = gg;
	}

	public String getGj() {
		return this.gj;
	}

	public void setGj(String gj) {
		this.gj = gj;
	}

	public String getGmyw() {
		return this.gmyw;
	}

	public void setGmyw(String gmyw) {
		this.gmyw = gmyw;
	}

	public String getGx() {
		return this.gx;
	}

	public void setGx(String gx) {
		this.gx = gx;
	}

	public String getGzdwjdz() {
		return this.gzdwjdz;
	}

	public void setGzdwjdz(String gzdwjdz) {
		this.gzdwjdz = gzdwjdz;
	}

	public String getHkdz() {
		return this.hkdz;
	}

	public void setHkdz(String hkdz) {
		this.hkdz = hkdz;
	}

	public Double getHlf() {
		return this.hlf == null ? 0 : this.hlf;
	}

	public void setHlf(Double hlf) {
		this.hlf = hlf;
	}

	public String getHy() {
		return this.hy;
	}

	public void setHy(String hy) {
		this.hy = hy;
	}

	public String getJbbm() {
		return this.jbbm;
	}

	public void setJbbm(String jbbm) {
		this.jbbm = jbbm;
	}

	public String getJbbm1() {
		return this.jbbm1;
	}

	public void setJbbm1(String jbbm1) {
		this.jbbm1 = jbbm1;
	}

	public String getJbbm2() {
		return this.jbbm2;
	}

	public void setJbbm2(String jbbm2) {
		this.jbbm2 = jbbm2;
	}

	public String getJbdm() {
		return this.jbdm;
	}

	public void setJbdm(String jbdm) {
		this.jbdm = jbdm;
	}

	public Double getJcyyclf() {
		return this.jcyyclf == null ? 0 : this.jcyyclf;
	}

	public void setJcyyclf(Double jcyyclf) {
		this.jcyyclf = jcyyclf;
	}

	public String getJgmc() {
		return this.jgmc;
	}

	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}

	public String getJkkh() {
		return this.jkkh;
	}

	public void setJkkh(String jkkh) {
		this.jkkh = jkkh;
	}

	public String getJxys() {
		return this.jxys;
	}

	public void setJxys(String jxys) {
		this.jxys = jxys;
	}

	public Double getKff() {
		return this.kff == null ? 0 : this.kff;
	}

	public void setKff(Double kff) {
		this.kff = kff;
	}

	public Double getKjywf() {
		return this.kjywf == null ? 0 : this.kjywf;
	}

	public void setKjywf(Double kjywf) {
		this.kjywf = kjywf;
	}

	public String getKzr() {
		return this.kzr;
	}

	public void setKzr(String kzr) {
		this.kzr = kzr;
	}

	public Double getLczdxmf() {
		return this.lczdxmf == null ? 0 : this.lczdxmf;
	}

	public void setLczdxmf(Double lczdxmf) {
		this.lczdxmf = lczdxmf;
	}

	public String getLxrxm() {
		return this.lxrxm;
	}

	public void setLxrxm(String lxrxm) {
		this.lxrxm = lxrxm;
	}

	public String getLyfs() {
		return this.lyfs;
	}

	public void setLyfs(String lyfs) {
		this.lyfs = lyfs;
	}

	public String getMd() {
		return this.md;
	}

	public void setMd(String md) {
		this.md = md;
	}

	public String getMz() {
		return this.mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}

	@Column(name="mzd_zyzd")
	public String getMzdZyzd() {
		return this.mzdZyzd;
	}

	@Column(name="mzd_zyzd")
	public void setMzdZyzd(String mzdZyzd) {
		this.mzdZyzd = mzdZyzd;
	}

	public Double getMzf() {
		return this.mzf == null ? 0 : this.mzf;
	}

	public void setMzf(Double mzf) {
		this.mzf = mzf;
	}

	public String getMzfs1() {
		return this.mzfs1;
	}

	public void setMzfs1(String mzfs1) {
		this.mzfs1 = mzfs1;
	}

	public String getMzfs10() {
		return this.mzfs10;
	}

	public void setMzfs10(String mzfs10) {
		this.mzfs10 = mzfs10;
	}

	public String getMzfs11() {
		return this.mzfs11;
	}

	public void setMzfs11(String mzfs11) {
		this.mzfs11 = mzfs11;
	}

	public String getMzfs12() {
		return this.mzfs12;
	}

	public void setMzfs12(String mzfs12) {
		this.mzfs12 = mzfs12;
	}

	public String getMzfs13() {
		return this.mzfs13;
	}

	public void setMzfs13(String mzfs13) {
		this.mzfs13 = mzfs13;
	}

	public String getMzfs14() {
		return this.mzfs14;
	}

	public void setMzfs14(String mzfs14) {
		this.mzfs14 = mzfs14;
	}

	public String getMzfs15() {
		return this.mzfs15;
	}

	public void setMzfs15(String mzfs15) {
		this.mzfs15 = mzfs15;
	}

	public String getMzfs16() {
		return this.mzfs16;
	}

	public void setMzfs16(String mzfs16) {
		this.mzfs16 = mzfs16;
	}

	public String getMzfs17() {
		return this.mzfs17;
	}

	public void setMzfs17(String mzfs17) {
		this.mzfs17 = mzfs17;
	}

	public String getMzfs18() {
		return this.mzfs18;
	}

	public void setMzfs18(String mzfs18) {
		this.mzfs18 = mzfs18;
	}

	public String getMzfs19() {
		return this.mzfs19;
	}

	public void setMzfs19(String mzfs19) {
		this.mzfs19 = mzfs19;
	}

	public String getMzfs2() {
		return this.mzfs2;
	}

	public void setMzfs2(String mzfs2) {
		this.mzfs2 = mzfs2;
	}

	public String getMzfs20() {
		return this.mzfs20;
	}

	public void setMzfs20(String mzfs20) {
		this.mzfs20 = mzfs20;
	}

	public String getMzfs21() {
		return this.mzfs21;
	}

	public void setMzfs21(String mzfs21) {
		this.mzfs21 = mzfs21;
	}

	public String getMzfs22() {
		return this.mzfs22;
	}

	public void setMzfs22(String mzfs22) {
		this.mzfs22 = mzfs22;
	}

	public String getMzfs23() {
		return this.mzfs23;
	}

	public void setMzfs23(String mzfs23) {
		this.mzfs23 = mzfs23;
	}

	public String getMzfs24() {
		return this.mzfs24;
	}

	public void setMzfs24(String mzfs24) {
		this.mzfs24 = mzfs24;
	}

	public String getMzfs25() {
		return this.mzfs25;
	}

	public void setMzfs25(String mzfs25) {
		this.mzfs25 = mzfs25;
	}

	public String getMzfs26() {
		return this.mzfs26;
	}

	public void setMzfs26(String mzfs26) {
		this.mzfs26 = mzfs26;
	}

	public String getMzfs27() {
		return this.mzfs27;
	}

	public void setMzfs27(String mzfs27) {
		this.mzfs27 = mzfs27;
	}

	public String getMzfs28() {
		return this.mzfs28;
	}

	public void setMzfs28(String mzfs28) {
		this.mzfs28 = mzfs28;
	}

	public String getMzfs29() {
		return this.mzfs29;
	}

	public void setMzfs29(String mzfs29) {
		this.mzfs29 = mzfs29;
	}

	public String getMzfs3() {
		return this.mzfs3;
	}

	public void setMzfs3(String mzfs3) {
		this.mzfs3 = mzfs3;
	}

	public String getMzfs30() {
		return this.mzfs30;
	}

	public void setMzfs30(String mzfs30) {
		this.mzfs30 = mzfs30;
	}

	public String getMzfs31() {
		return this.mzfs31;
	}

	public void setMzfs31(String mzfs31) {
		this.mzfs31 = mzfs31;
	}

	public String getMzfs32() {
		return this.mzfs32;
	}

	public void setMzfs32(String mzfs32) {
		this.mzfs32 = mzfs32;
	}

	public String getMzfs33() {
		return this.mzfs33;
	}

	public void setMzfs33(String mzfs33) {
		this.mzfs33 = mzfs33;
	}

	public String getMzfs34() {
		return this.mzfs34;
	}

	public void setMzfs34(String mzfs34) {
		this.mzfs34 = mzfs34;
	}

	public String getMzfs35() {
		return this.mzfs35;
	}

	public void setMzfs35(String mzfs35) {
		this.mzfs35 = mzfs35;
	}

	public String getMzfs36() {
		return this.mzfs36;
	}

	public void setMzfs36(String mzfs36) {
		this.mzfs36 = mzfs36;
	}

	public String getMzfs37() {
		return this.mzfs37;
	}

	public void setMzfs37(String mzfs37) {
		this.mzfs37 = mzfs37;
	}

	public String getMzfs38() {
		return this.mzfs38;
	}

	public void setMzfs38(String mzfs38) {
		this.mzfs38 = mzfs38;
	}

	public String getMzfs39() {
		return this.mzfs39;
	}

	public void setMzfs39(String mzfs39) {
		this.mzfs39 = mzfs39;
	}

	public String getMzfs4() {
		return this.mzfs4;
	}

	public void setMzfs4(String mzfs4) {
		this.mzfs4 = mzfs4;
	}

	public String getMzfs40() {
		return this.mzfs40;
	}

	public void setMzfs40(String mzfs40) {
		this.mzfs40 = mzfs40;
	}

	public String getMzfs41() {
		return this.mzfs41;
	}

	public void setMzfs41(String mzfs41) {
		this.mzfs41 = mzfs41;
	}

	public String getMzfs5() {
		return this.mzfs5;
	}

	public void setMzfs5(String mzfs5) {
		this.mzfs5 = mzfs5;
	}

	public String getMzfs6() {
		return this.mzfs6;
	}

	public void setMzfs6(String mzfs6) {
		this.mzfs6 = mzfs6;
	}

	public String getMzfs7() {
		return this.mzfs7;
	}

	public void setMzfs7(String mzfs7) {
		this.mzfs7 = mzfs7;
	}

	public String getMzfs8() {
		return this.mzfs8;
	}

	public void setMzfs8(String mzfs8) {
		this.mzfs8 = mzfs8;
	}

	public String getMzfs9() {
		return this.mzfs9;
	}

	public void setMzfs9(String mzfs9) {
		this.mzfs9 = mzfs9;
	}

	public String getMzys1() {
		return this.mzys1;
	}

	public void setMzys1(String mzys1) {
		this.mzys1 = mzys1;
	}

	public String getMzys10() {
		return this.mzys10;
	}

	public void setMzys10(String mzys10) {
		this.mzys10 = mzys10;
	}

	public String getMzys11() {
		return this.mzys11;
	}

	public void setMzys11(String mzys11) {
		this.mzys11 = mzys11;
	}

	public String getMzys12() {
		return this.mzys12;
	}

	public void setMzys12(String mzys12) {
		this.mzys12 = mzys12;
	}

	public String getMzys13() {
		return this.mzys13;
	}

	public void setMzys13(String mzys13) {
		this.mzys13 = mzys13;
	}

	public String getMzys14() {
		return this.mzys14;
	}

	public void setMzys14(String mzys14) {
		this.mzys14 = mzys14;
	}

	public String getMzys15() {
		return this.mzys15;
	}

	public void setMzys15(String mzys15) {
		this.mzys15 = mzys15;
	}

	public String getMzys16() {
		return this.mzys16;
	}

	public void setMzys16(String mzys16) {
		this.mzys16 = mzys16;
	}

	public String getMzys17() {
		return this.mzys17;
	}

	public void setMzys17(String mzys17) {
		this.mzys17 = mzys17;
	}

	public String getMzys18() {
		return this.mzys18;
	}

	public void setMzys18(String mzys18) {
		this.mzys18 = mzys18;
	}

	public String getMzys19() {
		return this.mzys19;
	}

	public void setMzys19(String mzys19) {
		this.mzys19 = mzys19;
	}

	public String getMzys2() {
		return this.mzys2;
	}

	public void setMzys2(String mzys2) {
		this.mzys2 = mzys2;
	}

	public String getMzys20() {
		return this.mzys20;
	}

	public void setMzys20(String mzys20) {
		this.mzys20 = mzys20;
	}

	public String getMzys21() {
		return this.mzys21;
	}

	public void setMzys21(String mzys21) {
		this.mzys21 = mzys21;
	}

	public String getMzys22() {
		return this.mzys22;
	}

	public void setMzys22(String mzys22) {
		this.mzys22 = mzys22;
	}

	public String getMzys23() {
		return this.mzys23;
	}

	public void setMzys23(String mzys23) {
		this.mzys23 = mzys23;
	}

	public String getMzys24() {
		return this.mzys24;
	}

	public void setMzys24(String mzys24) {
		this.mzys24 = mzys24;
	}

	public String getMzys25() {
		return this.mzys25;
	}

	public void setMzys25(String mzys25) {
		this.mzys25 = mzys25;
	}

	public String getMzys26() {
		return this.mzys26;
	}

	public void setMzys26(String mzys26) {
		this.mzys26 = mzys26;
	}

	public String getMzys27() {
		return this.mzys27;
	}

	public void setMzys27(String mzys27) {
		this.mzys27 = mzys27;
	}

	public String getMzys28() {
		return this.mzys28;
	}

	public void setMzys28(String mzys28) {
		this.mzys28 = mzys28;
	}

	public String getMzys29() {
		return this.mzys29;
	}

	public void setMzys29(String mzys29) {
		this.mzys29 = mzys29;
	}

	public String getMzys3() {
		return this.mzys3;
	}

	public void setMzys3(String mzys3) {
		this.mzys3 = mzys3;
	}

	public String getMzys30() {
		return this.mzys30;
	}

	public void setMzys30(String mzys30) {
		this.mzys30 = mzys30;
	}

	public String getMzys31() {
		return this.mzys31;
	}

	public void setMzys31(String mzys31) {
		this.mzys31 = mzys31;
	}

	public String getMzys32() {
		return this.mzys32;
	}

	public void setMzys32(String mzys32) {
		this.mzys32 = mzys32;
	}

	public String getMzys33() {
		return this.mzys33;
	}

	public void setMzys33(String mzys33) {
		this.mzys33 = mzys33;
	}

	public String getMzys34() {
		return this.mzys34;
	}

	public void setMzys34(String mzys34) {
		this.mzys34 = mzys34;
	}

	public String getMzys35() {
		return this.mzys35;
	}

	public void setMzys35(String mzys35) {
		this.mzys35 = mzys35;
	}

	public String getMzys36() {
		return this.mzys36;
	}

	public void setMzys36(String mzys36) {
		this.mzys36 = mzys36;
	}

	public String getMzys37() {
		return this.mzys37;
	}

	public void setMzys37(String mzys37) {
		this.mzys37 = mzys37;
	}

	public String getMzys38() {
		return this.mzys38;
	}

	public void setMzys38(String mzys38) {
		this.mzys38 = mzys38;
	}

	public String getMzys39() {
		return this.mzys39;
	}

	public void setMzys39(String mzys39) {
		this.mzys39 = mzys39;
	}

	public String getMzys4() {
		return this.mzys4;
	}

	public void setMzys4(String mzys4) {
		this.mzys4 = mzys4;
	}

	public String getMzys40() {
		return this.mzys40;
	}

	public void setMzys40(String mzys40) {
		this.mzys40 = mzys40;
	}

	public String getMzys41() {
		return this.mzys41;
	}

	public void setMzys41(String mzys41) {
		this.mzys41 = mzys41;
	}

	public String getMzys5() {
		return this.mzys5;
	}

	public void setMzys5(String mzys5) {
		this.mzys5 = mzys5;
	}

	public String getMzys6() {
		return this.mzys6;
	}

	public void setMzys6(String mzys6) {
		this.mzys6 = mzys6;
	}

	public String getMzys7() {
		return this.mzys7;
	}

	public void setMzys7(String mzys7) {
		this.mzys7 = mzys7;
	}

	public String getMzys8() {
		return this.mzys8;
	}

	public void setMzys8(String mzys8) {
		this.mzys8 = mzys8;
	}

	public String getMzys9() {
		return this.mzys9;
	}

	public void setMzys9(String mzys9) {
		this.mzys9 = mzys9;
	}

	@Column(name="mzzd_xyzd")
	public String getMzzdXyzd() {
		return this.mzzdXyzd;
	}

	@Column(name="mzzd_xyzd")
	public void setMzzdXyzd(String mzzdXyzd) {
		this.mzzdXyzd = mzzdXyzd;
	}

	public String getNl() {
		return this.nl;
	}

	public void setNl(String nl) {
		this.nl = nl;
	}

	public Double getNxyzlzpf() {
		return this.nxyzlzpf == null ? 0 : this.nxyzlzpf;
	}

	public void setNxyzlzpf(Double nxyzlzpf) {
		this.nxyzlzpf = nxyzlzpf;
	}

	public Double getQdblzpf() {
		return this.qdblzpf == null ? 0 : this.qdblzpf;
	}

	public void setQdblzpf(Double qdblzpf) {
		this.qdblzpf = qdblzpf;
	}

	public String getQkdj1() {
		return this.qkdj1;
	}

	public void setQkdj1(String qkdj1) {
		this.qkdj1 = qkdj1;
	}

	public String getQkdj10() {
		return this.qkdj10;
	}

	public void setQkdj10(String qkdj10) {
		this.qkdj10 = qkdj10;
	}

	public String getQkdj11() {
		return this.qkdj11;
	}

	public void setQkdj11(String qkdj11) {
		this.qkdj11 = qkdj11;
	}

	public String getQkdj12() {
		return this.qkdj12;
	}

	public void setQkdj12(String qkdj12) {
		this.qkdj12 = qkdj12;
	}

	public String getQkdj13() {
		return this.qkdj13;
	}

	public void setQkdj13(String qkdj13) {
		this.qkdj13 = qkdj13;
	}

	public String getQkdj14() {
		return this.qkdj14;
	}

	public void setQkdj14(String qkdj14) {
		this.qkdj14 = qkdj14;
	}

	public String getQkdj15() {
		return this.qkdj15;
	}

	public void setQkdj15(String qkdj15) {
		this.qkdj15 = qkdj15;
	}

	public String getQkdj16() {
		return this.qkdj16;
	}

	public void setQkdj16(String qkdj16) {
		this.qkdj16 = qkdj16;
	}

	public String getQkdj17() {
		return this.qkdj17;
	}

	public void setQkdj17(String qkdj17) {
		this.qkdj17 = qkdj17;
	}

	public String getQkdj18() {
		return this.qkdj18;
	}

	public void setQkdj18(String qkdj18) {
		this.qkdj18 = qkdj18;
	}

	public String getQkdj19() {
		return this.qkdj19;
	}

	public void setQkdj19(String qkdj19) {
		this.qkdj19 = qkdj19;
	}

	public String getQkdj2() {
		return this.qkdj2;
	}

	public void setQkdj2(String qkdj2) {
		this.qkdj2 = qkdj2;
	}

	public String getQkdj20() {
		return this.qkdj20;
	}

	public void setQkdj20(String qkdj20) {
		this.qkdj20 = qkdj20;
	}

	public String getQkdj21() {
		return this.qkdj21;
	}

	public void setQkdj21(String qkdj21) {
		this.qkdj21 = qkdj21;
	}

	public String getQkdj22() {
		return this.qkdj22;
	}

	public void setQkdj22(String qkdj22) {
		this.qkdj22 = qkdj22;
	}

	public String getQkdj23() {
		return this.qkdj23;
	}

	public void setQkdj23(String qkdj23) {
		this.qkdj23 = qkdj23;
	}

	public String getQkdj24() {
		return this.qkdj24;
	}

	public void setQkdj24(String qkdj24) {
		this.qkdj24 = qkdj24;
	}

	public String getQkdj25() {
		return this.qkdj25;
	}

	public void setQkdj25(String qkdj25) {
		this.qkdj25 = qkdj25;
	}

	public String getQkdj26() {
		return this.qkdj26;
	}

	public void setQkdj26(String qkdj26) {
		this.qkdj26 = qkdj26;
	}

	public String getQkdj27() {
		return this.qkdj27;
	}

	public void setQkdj27(String qkdj27) {
		this.qkdj27 = qkdj27;
	}

	public String getQkdj28() {
		return this.qkdj28;
	}

	public void setQkdj28(String qkdj28) {
		this.qkdj28 = qkdj28;
	}

	public String getQkdj29() {
		return this.qkdj29;
	}

	public void setQkdj29(String qkdj29) {
		this.qkdj29 = qkdj29;
	}

	public String getQkdj3() {
		return this.qkdj3;
	}

	public void setQkdj3(String qkdj3) {
		this.qkdj3 = qkdj3;
	}

	public String getQkdj30() {
		return this.qkdj30;
	}

	public void setQkdj30(String qkdj30) {
		this.qkdj30 = qkdj30;
	}

	public String getQkdj31() {
		return this.qkdj31;
	}

	public void setQkdj31(String qkdj31) {
		this.qkdj31 = qkdj31;
	}

	public String getQkdj32() {
		return this.qkdj32;
	}

	public void setQkdj32(String qkdj32) {
		this.qkdj32 = qkdj32;
	}

	public String getQkdj33() {
		return this.qkdj33;
	}

	public void setQkdj33(String qkdj33) {
		this.qkdj33 = qkdj33;
	}

	public String getQkdj34() {
		return this.qkdj34;
	}

	public void setQkdj34(String qkdj34) {
		this.qkdj34 = qkdj34;
	}

	public String getQkdj35() {
		return this.qkdj35;
	}

	public void setQkdj35(String qkdj35) {
		this.qkdj35 = qkdj35;
	}

	public String getQkdj36() {
		return this.qkdj36;
	}

	public void setQkdj36(String qkdj36) {
		this.qkdj36 = qkdj36;
	}

	public String getQkdj37() {
		return this.qkdj37;
	}

	public void setQkdj37(String qkdj37) {
		this.qkdj37 = qkdj37;
	}

	public String getQkdj38() {
		return this.qkdj38;
	}

	public void setQkdj38(String qkdj38) {
		this.qkdj38 = qkdj38;
	}

	public String getQkdj39() {
		return this.qkdj39;
	}

	public void setQkdj39(String qkdj39) {
		this.qkdj39 = qkdj39;
	}

	public String getQkdj4() {
		return this.qkdj4;
	}

	public void setQkdj4(String qkdj4) {
		this.qkdj4 = qkdj4;
	}

	public String getQkdj40() {
		return this.qkdj40;
	}

	public void setQkdj40(String qkdj40) {
		this.qkdj40 = qkdj40;
	}

	public String getQkdj41() {
		return this.qkdj41;
	}

	public void setQkdj41(String qkdj41) {
		this.qkdj41 = qkdj41;
	}

	public String getQkdj5() {
		return this.qkdj5;
	}

	public void setQkdj5(String qkdj5) {
		this.qkdj5 = qkdj5;
	}

	public String getQkdj6() {
		return this.qkdj6;
	}

	public void setQkdj6(String qkdj6) {
		this.qkdj6 = qkdj6;
	}

	public String getQkdj7() {
		return this.qkdj7;
	}

	public void setQkdj7(String qkdj7) {
		this.qkdj7 = qkdj7;
	}

	public String getQkdj8() {
		return this.qkdj8;
	}

	public void setQkdj8(String qkdj8) {
		this.qkdj8 = qkdj8;
	}

	public String getQkdj9() {
		return this.qkdj9;
	}

	public void setQkdj9(String qkdj9) {
		this.qkdj9 = qkdj9;
	}

	public String getQkylb1() {
		return this.qkylb1;
	}

	public void setQkylb1(String qkylb1) {
		this.qkylb1 = qkylb1;
	}

	public String getQkylb10() {
		return this.qkylb10;
	}

	public void setQkylb10(String qkylb10) {
		this.qkylb10 = qkylb10;
	}

	public String getQkylb11() {
		return this.qkylb11;
	}

	public void setQkylb11(String qkylb11) {
		this.qkylb11 = qkylb11;
	}

	public String getQkylb12() {
		return this.qkylb12;
	}

	public void setQkylb12(String qkylb12) {
		this.qkylb12 = qkylb12;
	}

	public String getQkylb13() {
		return this.qkylb13;
	}

	public void setQkylb13(String qkylb13) {
		this.qkylb13 = qkylb13;
	}

	public String getQkylb14() {
		return this.qkylb14;
	}

	public void setQkylb14(String qkylb14) {
		this.qkylb14 = qkylb14;
	}

	public String getQkylb15() {
		return this.qkylb15;
	}

	public void setQkylb15(String qkylb15) {
		this.qkylb15 = qkylb15;
	}

	public String getQkylb16() {
		return this.qkylb16;
	}

	public void setQkylb16(String qkylb16) {
		this.qkylb16 = qkylb16;
	}

	public String getQkylb17() {
		return this.qkylb17;
	}

	public void setQkylb17(String qkylb17) {
		this.qkylb17 = qkylb17;
	}

	public String getQkylb18() {
		return this.qkylb18;
	}

	public void setQkylb18(String qkylb18) {
		this.qkylb18 = qkylb18;
	}

	public String getQkylb19() {
		return this.qkylb19;
	}

	public void setQkylb19(String qkylb19) {
		this.qkylb19 = qkylb19;
	}

	public String getQkylb2() {
		return this.qkylb2;
	}

	public void setQkylb2(String qkylb2) {
		this.qkylb2 = qkylb2;
	}

	public String getQkylb20() {
		return this.qkylb20;
	}

	public void setQkylb20(String qkylb20) {
		this.qkylb20 = qkylb20;
	}

	public String getQkylb21() {
		return this.qkylb21;
	}

	public void setQkylb21(String qkylb21) {
		this.qkylb21 = qkylb21;
	}

	public String getQkylb22() {
		return this.qkylb22;
	}

	public void setQkylb22(String qkylb22) {
		this.qkylb22 = qkylb22;
	}

	public String getQkylb23() {
		return this.qkylb23;
	}

	public void setQkylb23(String qkylb23) {
		this.qkylb23 = qkylb23;
	}

	public String getQkylb24() {
		return this.qkylb24;
	}

	public void setQkylb24(String qkylb24) {
		this.qkylb24 = qkylb24;
	}

	public String getQkylb25() {
		return this.qkylb25;
	}

	public void setQkylb25(String qkylb25) {
		this.qkylb25 = qkylb25;
	}

	public String getQkylb26() {
		return this.qkylb26;
	}

	public void setQkylb26(String qkylb26) {
		this.qkylb26 = qkylb26;
	}

	public String getQkylb27() {
		return this.qkylb27;
	}

	public void setQkylb27(String qkylb27) {
		this.qkylb27 = qkylb27;
	}

	public String getQkylb28() {
		return this.qkylb28;
	}

	public void setQkylb28(String qkylb28) {
		this.qkylb28 = qkylb28;
	}

	public String getQkylb29() {
		return this.qkylb29;
	}

	public void setQkylb29(String qkylb29) {
		this.qkylb29 = qkylb29;
	}

	public String getQkylb3() {
		return this.qkylb3;
	}

	public void setQkylb3(String qkylb3) {
		this.qkylb3 = qkylb3;
	}

	public String getQkylb30() {
		return this.qkylb30;
	}

	public void setQkylb30(String qkylb30) {
		this.qkylb30 = qkylb30;
	}

	public String getQkylb31() {
		return this.qkylb31;
	}

	public void setQkylb31(String qkylb31) {
		this.qkylb31 = qkylb31;
	}

	public String getQkylb32() {
		return this.qkylb32;
	}

	public void setQkylb32(String qkylb32) {
		this.qkylb32 = qkylb32;
	}

	public String getQkylb33() {
		return this.qkylb33;
	}

	public void setQkylb33(String qkylb33) {
		this.qkylb33 = qkylb33;
	}

	public String getQkylb34() {
		return this.qkylb34;
	}

	public void setQkylb34(String qkylb34) {
		this.qkylb34 = qkylb34;
	}

	public String getQkylb35() {
		return this.qkylb35;
	}

	public void setQkylb35(String qkylb35) {
		this.qkylb35 = qkylb35;
	}

	public String getQkylb36() {
		return this.qkylb36;
	}

	public void setQkylb36(String qkylb36) {
		this.qkylb36 = qkylb36;
	}

	public String getQkylb37() {
		return this.qkylb37;
	}

	public void setQkylb37(String qkylb37) {
		this.qkylb37 = qkylb37;
	}

	public String getQkylb38() {
		return this.qkylb38;
	}

	public void setQkylb38(String qkylb38) {
		this.qkylb38 = qkylb38;
	}

	public String getQkylb39() {
		return this.qkylb39;
	}

	public void setQkylb39(String qkylb39) {
		this.qkylb39 = qkylb39;
	}

	public String getQkylb4() {
		return this.qkylb4;
	}

	public void setQkylb4(String qkylb4) {
		this.qkylb4 = qkylb4;
	}

	public String getQkylb40() {
		return this.qkylb40;
	}

	public void setQkylb40(String qkylb40) {
		this.qkylb40 = qkylb40;
	}

	public String getQkylb41() {
		return this.qkylb41;
	}

	public void setQkylb41(String qkylb41) {
		this.qkylb41 = qkylb41;
	}

	public String getQkylb5() {
		return this.qkylb5;
	}

	public void setQkylb5(String qkylb5) {
		this.qkylb5 = qkylb5;
	}

	public String getQkylb6() {
		return this.qkylb6;
	}

	public void setQkylb6(String qkylb6) {
		this.qkylb6 = qkylb6;
	}

	public String getQkylb7() {
		return this.qkylb7;
	}

	public void setQkylb7(String qkylb7) {
		this.qkylb7 = qkylb7;
	}

	public String getQkylb8() {
		return this.qkylb8;
	}

	public void setQkylb8(String qkylb8) {
		this.qkylb8 = qkylb8;
	}

	public String getQkylb9() {
		return this.qkylb9;
	}

	public void setQkylb9(String qkylb9) {
		this.qkylb9 = qkylb9;
	}

	public Double getQtf() {
		return this.qtf == null ? 0 : this.qtf;
	}

	public void setQtf(Double qtf) {
		this.qtf = qtf;
	}

	public Double getQtfy() {
		return this.qtfy == null ? 0 : this.qtfy;
	}

	public void setQtfy(Double qtfy) {
		this.qtfy = qtfy;
	}

	public String getQtzd1() {
		return this.qtzd1;
	}

	public void setQtzd1(String qtzd1) {
		this.qtzd1 = qtzd1;
	}

	public String getQtzd10() {
		return this.qtzd10;
	}

	public void setQtzd10(String qtzd10) {
		this.qtzd10 = qtzd10;
	}

	public String getQtzd11() {
		return this.qtzd11;
	}

	public void setQtzd11(String qtzd11) {
		this.qtzd11 = qtzd11;
	}

	public String getQtzd12() {
		return this.qtzd12;
	}

	public void setQtzd12(String qtzd12) {
		this.qtzd12 = qtzd12;
	}

	public String getQtzd13() {
		return this.qtzd13;
	}

	public void setQtzd13(String qtzd13) {
		this.qtzd13 = qtzd13;
	}

	public String getQtzd14() {
		return this.qtzd14;
	}

	public void setQtzd14(String qtzd14) {
		this.qtzd14 = qtzd14;
	}

	public String getQtzd15() {
		return this.qtzd15;
	}

	public void setQtzd15(String qtzd15) {
		this.qtzd15 = qtzd15;
	}

	public String getQtzd16() {
		return this.qtzd16;
	}

	public void setQtzd16(String qtzd16) {
		this.qtzd16 = qtzd16;
	}

	public String getQtzd17() {
		return this.qtzd17;
	}

	public void setQtzd17(String qtzd17) {
		this.qtzd17 = qtzd17;
	}

	public String getQtzd18() {
		return this.qtzd18;
	}

	public void setQtzd18(String qtzd18) {
		this.qtzd18 = qtzd18;
	}

	public String getQtzd19() {
		return this.qtzd19;
	}

	public void setQtzd19(String qtzd19) {
		this.qtzd19 = qtzd19;
	}

	public String getQtzd2() {
		return this.qtzd2;
	}

	public void setQtzd2(String qtzd2) {
		this.qtzd2 = qtzd2;
	}

	public String getQtzd20() {
		return this.qtzd20;
	}

	public void setQtzd20(String qtzd20) {
		this.qtzd20 = qtzd20;
	}

	public String getQtzd21() {
		return this.qtzd21;
	}

	public void setQtzd21(String qtzd21) {
		this.qtzd21 = qtzd21;
	}

	public String getQtzd22() {
		return this.qtzd22;
	}

	public void setQtzd22(String qtzd22) {
		this.qtzd22 = qtzd22;
	}

	public String getQtzd23() {
		return this.qtzd23;
	}

	public void setQtzd23(String qtzd23) {
		this.qtzd23 = qtzd23;
	}

	public String getQtzd24() {
		return this.qtzd24;
	}

	public void setQtzd24(String qtzd24) {
		this.qtzd24 = qtzd24;
	}

	public String getQtzd25() {
		return this.qtzd25;
	}

	public void setQtzd25(String qtzd25) {
		this.qtzd25 = qtzd25;
	}

	public String getQtzd26() {
		return this.qtzd26;
	}

	public void setQtzd26(String qtzd26) {
		this.qtzd26 = qtzd26;
	}

	public String getQtzd27() {
		return this.qtzd27;
	}

	public void setQtzd27(String qtzd27) {
		this.qtzd27 = qtzd27;
	}

	public String getQtzd28() {
		return this.qtzd28;
	}

	public void setQtzd28(String qtzd28) {
		this.qtzd28 = qtzd28;
	}

	public String getQtzd29() {
		return this.qtzd29;
	}

	public void setQtzd29(String qtzd29) {
		this.qtzd29 = qtzd29;
	}

	public String getQtzd3() {
		return this.qtzd3;
	}

	public void setQtzd3(String qtzd3) {
		this.qtzd3 = qtzd3;
	}

	public String getQtzd30() {
		return this.qtzd30;
	}

	public void setQtzd30(String qtzd30) {
		this.qtzd30 = qtzd30;
	}

	public String getQtzd31() {
		return this.qtzd31;
	}

	public void setQtzd31(String qtzd31) {
		this.qtzd31 = qtzd31;
	}

	public String getQtzd32() {
		return this.qtzd32;
	}

	public void setQtzd32(String qtzd32) {
		this.qtzd32 = qtzd32;
	}

	public String getQtzd33() {
		return this.qtzd33;
	}

	public void setQtzd33(String qtzd33) {
		this.qtzd33 = qtzd33;
	}

	public String getQtzd34() {
		return this.qtzd34;
	}

	public void setQtzd34(String qtzd34) {
		this.qtzd34 = qtzd34;
	}

	public String getQtzd35() {
		return this.qtzd35;
	}

	public void setQtzd35(String qtzd35) {
		this.qtzd35 = qtzd35;
	}

	public String getQtzd36() {
		return this.qtzd36;
	}

	public void setQtzd36(String qtzd36) {
		this.qtzd36 = qtzd36;
	}

	public String getQtzd37() {
		return this.qtzd37;
	}

	public void setQtzd37(String qtzd37) {
		this.qtzd37 = qtzd37;
	}

	public String getQtzd38() {
		return this.qtzd38;
	}

	public void setQtzd38(String qtzd38) {
		this.qtzd38 = qtzd38;
	}

	public String getQtzd39() {
		return this.qtzd39;
	}

	public void setQtzd39(String qtzd39) {
		this.qtzd39 = qtzd39;
	}

	public String getQtzd4() {
		return this.qtzd4;
	}

	public void setQtzd4(String qtzd4) {
		this.qtzd4 = qtzd4;
	}

	public String getQtzd40() {
		return this.qtzd40;
	}

	public void setQtzd40(String qtzd40) {
		this.qtzd40 = qtzd40;
	}

	public String getQtzd5() {
		return this.qtzd5;
	}

	public void setQtzd5(String qtzd5) {
		this.qtzd5 = qtzd5;
	}

	public String getQtzd6() {
		return this.qtzd6;
	}

	public void setQtzd6(String qtzd6) {
		this.qtzd6 = qtzd6;
	}

	public String getQtzd7() {
		return this.qtzd7;
	}

	public void setQtzd7(String qtzd7) {
		this.qtzd7 = qtzd7;
	}

	public String getQtzd8() {
		return this.qtzd8;
	}

	public void setQtzd8(String qtzd8) {
		this.qtzd8 = qtzd8;
	}

	public String getQtzd9() {
		return this.qtzd9;
	}

	public void setQtzd9(String qtzd9) {
		this.qtzd9 = qtzd9;
	}

	public String getRh() {
		return this.rh;
	}

	public void setRh(String rh) {
		this.rh = rh;
	}

	public String getRybf() {
		return this.rybf;
	}

	public void setRybf(String rybf) {
		this.rybf = rybf;
	}

	public String getRybq1() {
		return this.rybq1;
	}

	public void setRybq1(String rybq1) {
		this.rybq1 = rybq1;
	}

	public String getRybq10() {
		return this.rybq10;
	}

	public void setRybq10(String rybq10) {
		this.rybq10 = rybq10;
	}

	public String getRybq11() {
		return this.rybq11;
	}

	public void setRybq11(String rybq11) {
		this.rybq11 = rybq11;
	}

	public String getRybq12() {
		return this.rybq12;
	}

	public void setRybq12(String rybq12) {
		this.rybq12 = rybq12;
	}

	public String getRybq13() {
		return this.rybq13;
	}

	public void setRybq13(String rybq13) {
		this.rybq13 = rybq13;
	}

	public String getRybq14() {
		return this.rybq14;
	}

	public void setRybq14(String rybq14) {
		this.rybq14 = rybq14;
	}

	public String getRybq15() {
		return this.rybq15;
	}

	public void setRybq15(String rybq15) {
		this.rybq15 = rybq15;
	}

	public String getRybq16() {
		return this.rybq16;
	}

	public void setRybq16(String rybq16) {
		this.rybq16 = rybq16;
	}

	public String getRybq17() {
		return this.rybq17;
	}

	public void setRybq17(String rybq17) {
		this.rybq17 = rybq17;
	}

	public String getRybq18() {
		return this.rybq18;
	}

	public void setRybq18(String rybq18) {
		this.rybq18 = rybq18;
	}

	public String getRybq19() {
		return this.rybq19;
	}

	public void setRybq19(String rybq19) {
		this.rybq19 = rybq19;
	}

	public String getRybq2() {
		return this.rybq2;
	}

	public void setRybq2(String rybq2) {
		this.rybq2 = rybq2;
	}

	public String getRybq20() {
		return this.rybq20;
	}

	public void setRybq20(String rybq20) {
		this.rybq20 = rybq20;
	}

	public String getRybq21() {
		return this.rybq21;
	}

	public void setRybq21(String rybq21) {
		this.rybq21 = rybq21;
	}

	public String getRybq22() {
		return this.rybq22;
	}

	public void setRybq22(String rybq22) {
		this.rybq22 = rybq22;
	}

	public String getRybq23() {
		return this.rybq23;
	}

	public void setRybq23(String rybq23) {
		this.rybq23 = rybq23;
	}

	public String getRybq24() {
		return this.rybq24;
	}

	public void setRybq24(String rybq24) {
		this.rybq24 = rybq24;
	}

	public String getRybq25() {
		return this.rybq25;
	}

	public void setRybq25(String rybq25) {
		this.rybq25 = rybq25;
	}

	public String getRybq26() {
		return this.rybq26;
	}

	public void setRybq26(String rybq26) {
		this.rybq26 = rybq26;
	}

	public String getRybq27() {
		return this.rybq27;
	}

	public void setRybq27(String rybq27) {
		this.rybq27 = rybq27;
	}

	public String getRybq28() {
		return this.rybq28;
	}

	public void setRybq28(String rybq28) {
		this.rybq28 = rybq28;
	}

	public String getRybq29() {
		return this.rybq29;
	}

	public void setRybq29(String rybq29) {
		this.rybq29 = rybq29;
	}

	public String getRybq3() {
		return this.rybq3;
	}

	public void setRybq3(String rybq3) {
		this.rybq3 = rybq3;
	}

	public String getRybq30() {
		return this.rybq30;
	}

	public void setRybq30(String rybq30) {
		this.rybq30 = rybq30;
	}

	public String getRybq31() {
		return this.rybq31;
	}

	public void setRybq31(String rybq31) {
		this.rybq31 = rybq31;
	}

	public String getRybq32() {
		return this.rybq32;
	}

	public void setRybq32(String rybq32) {
		this.rybq32 = rybq32;
	}

	public String getRybq33() {
		return this.rybq33;
	}

	public void setRybq33(String rybq33) {
		this.rybq33 = rybq33;
	}

	public String getRybq34() {
		return this.rybq34;
	}

	public void setRybq34(String rybq34) {
		this.rybq34 = rybq34;
	}

	public String getRybq35() {
		return this.rybq35;
	}

	public void setRybq35(String rybq35) {
		this.rybq35 = rybq35;
	}

	public String getRybq36() {
		return this.rybq36;
	}

	public void setRybq36(String rybq36) {
		this.rybq36 = rybq36;
	}

	public String getRybq37() {
		return this.rybq37;
	}

	public void setRybq37(String rybq37) {
		this.rybq37 = rybq37;
	}

	public String getRybq38() {
		return this.rybq38;
	}

	public void setRybq38(String rybq38) {
		this.rybq38 = rybq38;
	}

	public String getRybq39() {
		return this.rybq39;
	}

	public void setRybq39(String rybq39) {
		this.rybq39 = rybq39;
	}

	public String getRybq4() {
		return this.rybq4;
	}

	public void setRybq4(String rybq4) {
		this.rybq4 = rybq4;
	}

	public String getRybq40() {
		return this.rybq40;
	}

	public void setRybq40(String rybq40) {
		this.rybq40 = rybq40;
	}

	public String getRybq5() {
		return this.rybq5;
	}

	public void setRybq5(String rybq5) {
		this.rybq5 = rybq5;
	}

	public String getRybq6() {
		return this.rybq6;
	}

	public void setRybq6(String rybq6) {
		this.rybq6 = rybq6;
	}

	public String getRybq7() {
		return this.rybq7;
	}

	public void setRybq7(String rybq7) {
		this.rybq7 = rybq7;
	}

	public String getRybq8() {
		return this.rybq8;
	}

	public void setRybq8(String rybq8) {
		this.rybq8 = rybq8;
	}

	public String getRybq9() {
		return this.rybq9;
	}

	public void setRybq9(String rybq9) {
		this.rybq9 = rybq9;
	}

	@Column(name="ryh_fz")
	public String getRyhFz() {
		return this.ryhFz;
	}

	@Column(name="ryh_fz")
	public void setRyhFz(String ryhFz) {
		this.ryhFz = ryhFz;
	}

	@Column(name="ryh_t")
	public String getRyhT() {
		return this.ryhT;
	}

	@Column(name="ryh_t")
	public void setRyhT(String ryhT) {
		this.ryhT = ryhT;
	}

	@Column(name="ryh_xs")
	public String getRyhXs() {
		return this.ryhXs;
	}

	@Column(name="ryh_xs")
	public void setRyhXs(String ryhXs) {
		this.ryhXs = ryhXs;
	}

	public String getRykb() {
		return this.rykb;
	}

	public void setRykb(String rykb) {
		this.rykb = rykb;
	}

	@Column(name="ryq_fz")
	public String getRyqFz() {
		return this.ryqFz;
	}

	@Column(name="ryq_fz")
	public void setRyqFz(String ryqFz) {
		this.ryqFz = ryqFz;
	}

	@Column(name="ryq_t")
	public String getRyqT() {
		return this.ryqT;
	}

	@Column(name="ryq_t")
	public void setRyqT(String ryqT) {
		this.ryqT = ryqT;
	}

	@Column(name="ryq_xs")
	public String getRyqXs() {
		return this.ryqXs;
	}

	@Column(name="ryq_xs")
	public void setRyqXs(String ryqXs) {
		this.ryqXs = ryqXs;
	}

	public String getRysj() {
		return this.rysj;
	}

	public void setRysj(String rysj) {
		this.rysj = rysj;
	}

	@Column(name="rysj_s")
	public String getRysjS() {
		return this.rysjS;
	}

	@Column(name="rysj_s")
	public void setRysjS(String rysjS) {
		this.rysjS = rysjS;
	}

	public String getRytj() {
		return this.rytj;
	}

	public void setRytj(String rytj) {
		this.rytj = rytj;
	}

	public String getSfzh() {
		return this.sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getShjb1() {
		return this.shjb1;
	}

	public void setShjb1(String shjb1) {
		this.shjb1 = shjb1;
	}

	public String getShjb10() {
		return this.shjb10;
	}

	public void setShjb10(String shjb10) {
		this.shjb10 = shjb10;
	}

	public String getShjb11() {
		return this.shjb11;
	}

	public void setShjb11(String shjb11) {
		this.shjb11 = shjb11;
	}

	public String getShjb12() {
		return this.shjb12;
	}

	public void setShjb12(String shjb12) {
		this.shjb12 = shjb12;
	}

	public String getShjb13() {
		return this.shjb13;
	}

	public void setShjb13(String shjb13) {
		this.shjb13 = shjb13;
	}

	public String getShjb14() {
		return this.shjb14;
	}

	public void setShjb14(String shjb14) {
		this.shjb14 = shjb14;
	}

	public String getShjb15() {
		return this.shjb15;
	}

	public void setShjb15(String shjb15) {
		this.shjb15 = shjb15;
	}

	public String getShjb16() {
		return this.shjb16;
	}

	public void setShjb16(String shjb16) {
		this.shjb16 = shjb16;
	}

	public String getShjb17() {
		return this.shjb17;
	}

	public void setShjb17(String shjb17) {
		this.shjb17 = shjb17;
	}

	public String getShjb18() {
		return this.shjb18;
	}

	public void setShjb18(String shjb18) {
		this.shjb18 = shjb18;
	}

	public String getShjb19() {
		return this.shjb19;
	}

	public void setShjb19(String shjb19) {
		this.shjb19 = shjb19;
	}

	public String getShjb2() {
		return this.shjb2;
	}

	public void setShjb2(String shjb2) {
		this.shjb2 = shjb2;
	}

	public String getShjb20() {
		return this.shjb20;
	}

	public void setShjb20(String shjb20) {
		this.shjb20 = shjb20;
	}

	public String getShjb21() {
		return this.shjb21;
	}

	public void setShjb21(String shjb21) {
		this.shjb21 = shjb21;
	}

	public String getShjb22() {
		return this.shjb22;
	}

	public void setShjb22(String shjb22) {
		this.shjb22 = shjb22;
	}

	public String getShjb23() {
		return this.shjb23;
	}

	public void setShjb23(String shjb23) {
		this.shjb23 = shjb23;
	}

	public String getShjb24() {
		return this.shjb24;
	}

	public void setShjb24(String shjb24) {
		this.shjb24 = shjb24;
	}

	public String getShjb25() {
		return this.shjb25;
	}

	public void setShjb25(String shjb25) {
		this.shjb25 = shjb25;
	}

	public String getShjb26() {
		return this.shjb26;
	}

	public void setShjb26(String shjb26) {
		this.shjb26 = shjb26;
	}

	public String getShjb27() {
		return this.shjb27;
	}

	public void setShjb27(String shjb27) {
		this.shjb27 = shjb27;
	}

	public String getShjb28() {
		return this.shjb28;
	}

	public void setShjb28(String shjb28) {
		this.shjb28 = shjb28;
	}

	public String getShjb29() {
		return this.shjb29;
	}

	public void setShjb29(String shjb29) {
		this.shjb29 = shjb29;
	}

	public String getShjb3() {
		return this.shjb3;
	}

	public void setShjb3(String shjb3) {
		this.shjb3 = shjb3;
	}

	public String getShjb30() {
		return this.shjb30;
	}

	public void setShjb30(String shjb30) {
		this.shjb30 = shjb30;
	}

	public String getShjb31() {
		return this.shjb31;
	}

	public void setShjb31(String shjb31) {
		this.shjb31 = shjb31;
	}

	public String getShjb32() {
		return this.shjb32;
	}

	public void setShjb32(String shjb32) {
		this.shjb32 = shjb32;
	}

	public String getShjb33() {
		return this.shjb33;
	}

	public void setShjb33(String shjb33) {
		this.shjb33 = shjb33;
	}

	public String getShjb34() {
		return this.shjb34;
	}

	public void setShjb34(String shjb34) {
		this.shjb34 = shjb34;
	}

	public String getShjb35() {
		return this.shjb35;
	}

	public void setShjb35(String shjb35) {
		this.shjb35 = shjb35;
	}

	public String getShjb36() {
		return this.shjb36;
	}

	public void setShjb36(String shjb36) {
		this.shjb36 = shjb36;
	}

	public String getShjb37() {
		return this.shjb37;
	}

	public void setShjb37(String shjb37) {
		this.shjb37 = shjb37;
	}

	public String getShjb38() {
		return this.shjb38;
	}

	public void setShjb38(String shjb38) {
		this.shjb38 = shjb38;
	}

	public String getShjb39() {
		return this.shjb39;
	}

	public void setShjb39(String shjb39) {
		this.shjb39 = shjb39;
	}

	public String getShjb4() {
		return this.shjb4;
	}

	public void setShjb4(String shjb4) {
		this.shjb4 = shjb4;
	}

	public String getShjb40() {
		return this.shjb40;
	}

	public void setShjb40(String shjb40) {
		this.shjb40 = shjb40;
	}

	public String getShjb41() {
		return this.shjb41;
	}

	public void setShjb41(String shjb41) {
		this.shjb41 = shjb41;
	}

	public String getShjb5() {
		return this.shjb5;
	}

	public void setShjb5(String shjb5) {
		this.shjb5 = shjb5;
	}

	public String getShjb6() {
		return this.shjb6;
	}

	public void setShjb6(String shjb6) {
		this.shjb6 = shjb6;
	}

	public String getShjb7() {
		return this.shjb7;
	}

	public void setShjb7(String shjb7) {
		this.shjb7 = shjb7;
	}

	public String getShjb8() {
		return this.shjb8;
	}

	public void setShjb8(String shjb8) {
		this.shjb8 = shjb8;
	}

	public String getShjb9() {
		return this.shjb9;
	}

	public void setShjb9(String shjb9) {
		this.shjb9 = shjb9;
	}

	public String getSj() {
		return this.sj;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}

	public String getSjzy() {
		return this.sjzy;
	}

	public void setSjzy(String sjzy) {
		this.sjzy = sjzy;
	}

	public Double getSsf() {
		return this.ssf == null ? 0 : this.ssf;
	}

	public void setSsf(Double ssf) {
		this.ssf = ssf;
	}

	public String getSsjczbm1() {
		return this.ssjczbm1;
	}

	public void setSsjczbm1(String ssjczbm1) {
		this.ssjczbm1 = ssjczbm1;
	}

	public String getSsjczbm10() {
		return this.ssjczbm10;
	}

	public void setSsjczbm10(String ssjczbm10) {
		this.ssjczbm10 = ssjczbm10;
	}

	public String getSsjczbm11() {
		return this.ssjczbm11;
	}

	public void setSsjczbm11(String ssjczbm11) {
		this.ssjczbm11 = ssjczbm11;
	}

	public String getSsjczbm12() {
		return this.ssjczbm12;
	}

	public void setSsjczbm12(String ssjczbm12) {
		this.ssjczbm12 = ssjczbm12;
	}

	public String getSsjczbm13() {
		return this.ssjczbm13;
	}

	public void setSsjczbm13(String ssjczbm13) {
		this.ssjczbm13 = ssjczbm13;
	}

	public String getSsjczbm14() {
		return this.ssjczbm14;
	}

	public void setSsjczbm14(String ssjczbm14) {
		this.ssjczbm14 = ssjczbm14;
	}

	public String getSsjczbm15() {
		return this.ssjczbm15;
	}

	public void setSsjczbm15(String ssjczbm15) {
		this.ssjczbm15 = ssjczbm15;
	}

	public String getSsjczbm16() {
		return this.ssjczbm16;
	}

	public void setSsjczbm16(String ssjczbm16) {
		this.ssjczbm16 = ssjczbm16;
	}

	public String getSsjczbm17() {
		return this.ssjczbm17;
	}

	public void setSsjczbm17(String ssjczbm17) {
		this.ssjczbm17 = ssjczbm17;
	}

	public String getSsjczbm18() {
		return this.ssjczbm18;
	}

	public void setSsjczbm18(String ssjczbm18) {
		this.ssjczbm18 = ssjczbm18;
	}

	public String getSsjczbm19() {
		return this.ssjczbm19;
	}

	public void setSsjczbm19(String ssjczbm19) {
		this.ssjczbm19 = ssjczbm19;
	}

	public String getSsjczbm2() {
		return this.ssjczbm2;
	}

	public void setSsjczbm2(String ssjczbm2) {
		this.ssjczbm2 = ssjczbm2;
	}

	public String getSsjczbm20() {
		return this.ssjczbm20;
	}

	public void setSsjczbm20(String ssjczbm20) {
		this.ssjczbm20 = ssjczbm20;
	}

	public String getSsjczbm21() {
		return this.ssjczbm21;
	}

	public void setSsjczbm21(String ssjczbm21) {
		this.ssjczbm21 = ssjczbm21;
	}

	public String getSsjczbm22() {
		return this.ssjczbm22;
	}

	public void setSsjczbm22(String ssjczbm22) {
		this.ssjczbm22 = ssjczbm22;
	}

	public String getSsjczbm23() {
		return this.ssjczbm23;
	}

	public void setSsjczbm23(String ssjczbm23) {
		this.ssjczbm23 = ssjczbm23;
	}

	public String getSsjczbm24() {
		return this.ssjczbm24;
	}

	public void setSsjczbm24(String ssjczbm24) {
		this.ssjczbm24 = ssjczbm24;
	}

	public String getSsjczbm25() {
		return this.ssjczbm25;
	}

	public void setSsjczbm25(String ssjczbm25) {
		this.ssjczbm25 = ssjczbm25;
	}

	public String getSsjczbm26() {
		return this.ssjczbm26;
	}

	public void setSsjczbm26(String ssjczbm26) {
		this.ssjczbm26 = ssjczbm26;
	}

	public String getSsjczbm27() {
		return this.ssjczbm27;
	}

	public void setSsjczbm27(String ssjczbm27) {
		this.ssjczbm27 = ssjczbm27;
	}

	public String getSsjczbm28() {
		return this.ssjczbm28;
	}

	public void setSsjczbm28(String ssjczbm28) {
		this.ssjczbm28 = ssjczbm28;
	}

	public String getSsjczbm29() {
		return this.ssjczbm29;
	}

	public void setSsjczbm29(String ssjczbm29) {
		this.ssjczbm29 = ssjczbm29;
	}

	public String getSsjczbm3() {
		return this.ssjczbm3;
	}

	public void setSsjczbm3(String ssjczbm3) {
		this.ssjczbm3 = ssjczbm3;
	}

	public String getSsjczbm30() {
		return this.ssjczbm30;
	}

	public void setSsjczbm30(String ssjczbm30) {
		this.ssjczbm30 = ssjczbm30;
	}

	public String getSsjczbm31() {
		return this.ssjczbm31;
	}

	public void setSsjczbm31(String ssjczbm31) {
		this.ssjczbm31 = ssjczbm31;
	}

	public String getSsjczbm32() {
		return this.ssjczbm32;
	}

	public void setSsjczbm32(String ssjczbm32) {
		this.ssjczbm32 = ssjczbm32;
	}

	public String getSsjczbm33() {
		return this.ssjczbm33;
	}

	public void setSsjczbm33(String ssjczbm33) {
		this.ssjczbm33 = ssjczbm33;
	}

	public String getSsjczbm34() {
		return this.ssjczbm34;
	}

	public void setSsjczbm34(String ssjczbm34) {
		this.ssjczbm34 = ssjczbm34;
	}

	public String getSsjczbm35() {
		return this.ssjczbm35;
	}

	public void setSsjczbm35(String ssjczbm35) {
		this.ssjczbm35 = ssjczbm35;
	}

	public String getSsjczbm36() {
		return this.ssjczbm36;
	}

	public void setSsjczbm36(String ssjczbm36) {
		this.ssjczbm36 = ssjczbm36;
	}

	public String getSsjczbm37() {
		return this.ssjczbm37;
	}

	public void setSsjczbm37(String ssjczbm37) {
		this.ssjczbm37 = ssjczbm37;
	}

	public String getSsjczbm38() {
		return this.ssjczbm38;
	}

	public void setSsjczbm38(String ssjczbm38) {
		this.ssjczbm38 = ssjczbm38;
	}

	public String getSsjczbm39() {
		return this.ssjczbm39;
	}

	public void setSsjczbm39(String ssjczbm39) {
		this.ssjczbm39 = ssjczbm39;
	}

	public String getSsjczbm4() {
		return this.ssjczbm4;
	}

	public void setSsjczbm4(String ssjczbm4) {
		this.ssjczbm4 = ssjczbm4;
	}

	public String getSsjczbm40() {
		return this.ssjczbm40;
	}

	public void setSsjczbm40(String ssjczbm40) {
		this.ssjczbm40 = ssjczbm40;
	}

	public String getSsjczbm41() {
		return this.ssjczbm41;
	}

	public void setSsjczbm41(String ssjczbm41) {
		this.ssjczbm41 = ssjczbm41;
	}

	public String getSsjczbm5() {
		return this.ssjczbm5;
	}

	public void setSsjczbm5(String ssjczbm5) {
		this.ssjczbm5 = ssjczbm5;
	}

	public String getSsjczbm6() {
		return this.ssjczbm6;
	}

	public void setSsjczbm6(String ssjczbm6) {
		this.ssjczbm6 = ssjczbm6;
	}

	public String getSsjczbm7() {
		return this.ssjczbm7;
	}

	public void setSsjczbm7(String ssjczbm7) {
		this.ssjczbm7 = ssjczbm7;
	}

	public String getSsjczbm8() {
		return this.ssjczbm8;
	}

	public void setSsjczbm8(String ssjczbm8) {
		this.ssjczbm8 = ssjczbm8;
	}

	public String getSsjczbm9() {
		return this.ssjczbm9;
	}

	public void setSsjczbm9(String ssjczbm9) {
		this.ssjczbm9 = ssjczbm9;
	}

	public String getSsjczmc1() {
		return this.ssjczmc1;
	}

	public void setSsjczmc1(String ssjczmc1) {
		this.ssjczmc1 = ssjczmc1;
	}

	public String getSsjczmc10() {
		return this.ssjczmc10;
	}

	public void setSsjczmc10(String ssjczmc10) {
		this.ssjczmc10 = ssjczmc10;
	}

	public String getSsjczmc11() {
		return this.ssjczmc11;
	}

	public void setSsjczmc11(String ssjczmc11) {
		this.ssjczmc11 = ssjczmc11;
	}

	public String getSsjczmc12() {
		return this.ssjczmc12;
	}

	public void setSsjczmc12(String ssjczmc12) {
		this.ssjczmc12 = ssjczmc12;
	}

	public String getSsjczmc13() {
		return this.ssjczmc13;
	}

	public void setSsjczmc13(String ssjczmc13) {
		this.ssjczmc13 = ssjczmc13;
	}

	public String getSsjczmc14() {
		return this.ssjczmc14;
	}

	public void setSsjczmc14(String ssjczmc14) {
		this.ssjczmc14 = ssjczmc14;
	}

	public String getSsjczmc15() {
		return this.ssjczmc15;
	}

	public void setSsjczmc15(String ssjczmc15) {
		this.ssjczmc15 = ssjczmc15;
	}

	public String getSsjczmc16() {
		return this.ssjczmc16;
	}

	public void setSsjczmc16(String ssjczmc16) {
		this.ssjczmc16 = ssjczmc16;
	}

	public String getSsjczmc17() {
		return this.ssjczmc17;
	}

	public void setSsjczmc17(String ssjczmc17) {
		this.ssjczmc17 = ssjczmc17;
	}

	public String getSsjczmc18() {
		return this.ssjczmc18;
	}

	public void setSsjczmc18(String ssjczmc18) {
		this.ssjczmc18 = ssjczmc18;
	}

	public String getSsjczmc19() {
		return this.ssjczmc19;
	}

	public void setSsjczmc19(String ssjczmc19) {
		this.ssjczmc19 = ssjczmc19;
	}

	public String getSsjczmc2() {
		return this.ssjczmc2;
	}

	public void setSsjczmc2(String ssjczmc2) {
		this.ssjczmc2 = ssjczmc2;
	}

	public String getSsjczmc20() {
		return this.ssjczmc20;
	}

	public void setSsjczmc20(String ssjczmc20) {
		this.ssjczmc20 = ssjczmc20;
	}

	public String getSsjczmc21() {
		return this.ssjczmc21;
	}

	public void setSsjczmc21(String ssjczmc21) {
		this.ssjczmc21 = ssjczmc21;
	}

	public String getSsjczmc22() {
		return this.ssjczmc22;
	}

	public void setSsjczmc22(String ssjczmc22) {
		this.ssjczmc22 = ssjczmc22;
	}

	public String getSsjczmc23() {
		return this.ssjczmc23;
	}

	public void setSsjczmc23(String ssjczmc23) {
		this.ssjczmc23 = ssjczmc23;
	}

	public String getSsjczmc24() {
		return this.ssjczmc24;
	}

	public void setSsjczmc24(String ssjczmc24) {
		this.ssjczmc24 = ssjczmc24;
	}

	public String getSsjczmc25() {
		return this.ssjczmc25;
	}

	public void setSsjczmc25(String ssjczmc25) {
		this.ssjczmc25 = ssjczmc25;
	}

	public String getSsjczmc26() {
		return this.ssjczmc26;
	}

	public void setSsjczmc26(String ssjczmc26) {
		this.ssjczmc26 = ssjczmc26;
	}

	public String getSsjczmc27() {
		return this.ssjczmc27;
	}

	public void setSsjczmc27(String ssjczmc27) {
		this.ssjczmc27 = ssjczmc27;
	}

	public String getSsjczmc28() {
		return this.ssjczmc28;
	}

	public void setSsjczmc28(String ssjczmc28) {
		this.ssjczmc28 = ssjczmc28;
	}

	public String getSsjczmc29() {
		return this.ssjczmc29;
	}

	public void setSsjczmc29(String ssjczmc29) {
		this.ssjczmc29 = ssjczmc29;
	}

	public String getSsjczmc3() {
		return this.ssjczmc3;
	}

	public void setSsjczmc3(String ssjczmc3) {
		this.ssjczmc3 = ssjczmc3;
	}

	public String getSsjczmc30() {
		return this.ssjczmc30;
	}

	public void setSsjczmc30(String ssjczmc30) {
		this.ssjczmc30 = ssjczmc30;
	}

	public String getSsjczmc31() {
		return this.ssjczmc31;
	}

	public void setSsjczmc31(String ssjczmc31) {
		this.ssjczmc31 = ssjczmc31;
	}

	public String getSsjczmc32() {
		return this.ssjczmc32;
	}

	public void setSsjczmc32(String ssjczmc32) {
		this.ssjczmc32 = ssjczmc32;
	}

	public String getSsjczmc33() {
		return this.ssjczmc33;
	}

	public void setSsjczmc33(String ssjczmc33) {
		this.ssjczmc33 = ssjczmc33;
	}

	public String getSsjczmc34() {
		return this.ssjczmc34;
	}

	public void setSsjczmc34(String ssjczmc34) {
		this.ssjczmc34 = ssjczmc34;
	}

	public String getSsjczmc35() {
		return this.ssjczmc35;
	}

	public void setSsjczmc35(String ssjczmc35) {
		this.ssjczmc35 = ssjczmc35;
	}

	public String getSsjczmc36() {
		return this.ssjczmc36;
	}

	public void setSsjczmc36(String ssjczmc36) {
		this.ssjczmc36 = ssjczmc36;
	}

	public String getSsjczmc37() {
		return this.ssjczmc37;
	}

	public void setSsjczmc37(String ssjczmc37) {
		this.ssjczmc37 = ssjczmc37;
	}

	public String getSsjczmc38() {
		return this.ssjczmc38;
	}

	public void setSsjczmc38(String ssjczmc38) {
		this.ssjczmc38 = ssjczmc38;
	}

	public String getSsjczmc39() {
		return this.ssjczmc39;
	}

	public void setSsjczmc39(String ssjczmc39) {
		this.ssjczmc39 = ssjczmc39;
	}

	public String getSsjczmc4() {
		return this.ssjczmc4;
	}

	public void setSsjczmc4(String ssjczmc4) {
		this.ssjczmc4 = ssjczmc4;
	}

	public String getSsjczmc40() {
		return this.ssjczmc40;
	}

	public void setSsjczmc40(String ssjczmc40) {
		this.ssjczmc40 = ssjczmc40;
	}

	public String getSsjczmc41() {
		return this.ssjczmc41;
	}

	public void setSsjczmc41(String ssjczmc41) {
		this.ssjczmc41 = ssjczmc41;
	}

	public String getSsjczmc5() {
		return this.ssjczmc5;
	}

	public void setSsjczmc5(String ssjczmc5) {
		this.ssjczmc5 = ssjczmc5;
	}

	public String getSsjczmc6() {
		return this.ssjczmc6;
	}

	public void setSsjczmc6(String ssjczmc6) {
		this.ssjczmc6 = ssjczmc6;
	}

	public String getSsjczmc7() {
		return this.ssjczmc7;
	}

	public void setSsjczmc7(String ssjczmc7) {
		this.ssjczmc7 = ssjczmc7;
	}

	public String getSsjczmc8() {
		return this.ssjczmc8;
	}

	public void setSsjczmc8(String ssjczmc8) {
		this.ssjczmc8 = ssjczmc8;
	}

	public String getSsjczmc9() {
		return this.ssjczmc9;
	}

	public void setSsjczmc9(String ssjczmc9) {
		this.ssjczmc9 = ssjczmc9;
	}

	public String getSsjczrq1() {
		return this.ssjczrq1;
	}

	public void setSsjczrq1(String ssjczrq1) {
		this.ssjczrq1 = ssjczrq1;
	}

	public String getSsjczrq10() {
		return this.ssjczrq10;
	}

	public void setSsjczrq10(String ssjczrq10) {
		this.ssjczrq10 = ssjczrq10;
	}

	public String getSsjczrq11() {
		return this.ssjczrq11;
	}

	public void setSsjczrq11(String ssjczrq11) {
		this.ssjczrq11 = ssjczrq11;
	}

	public String getSsjczrq12() {
		return this.ssjczrq12;
	}

	public void setSsjczrq12(String ssjczrq12) {
		this.ssjczrq12 = ssjczrq12;
	}

	public String getSsjczrq13() {
		return this.ssjczrq13;
	}

	public void setSsjczrq13(String ssjczrq13) {
		this.ssjczrq13 = ssjczrq13;
	}

	public String getSsjczrq14() {
		return this.ssjczrq14;
	}

	public void setSsjczrq14(String ssjczrq14) {
		this.ssjczrq14 = ssjczrq14;
	}

	public String getSsjczrq15() {
		return this.ssjczrq15;
	}

	public void setSsjczrq15(String ssjczrq15) {
		this.ssjczrq15 = ssjczrq15;
	}

	public String getSsjczrq16() {
		return this.ssjczrq16;
	}

	public void setSsjczrq16(String ssjczrq16) {
		this.ssjczrq16 = ssjczrq16;
	}

	public String getSsjczrq17() {
		return this.ssjczrq17;
	}

	public void setSsjczrq17(String ssjczrq17) {
		this.ssjczrq17 = ssjczrq17;
	}

	public String getSsjczrq18() {
		return this.ssjczrq18;
	}

	public void setSsjczrq18(String ssjczrq18) {
		this.ssjczrq18 = ssjczrq18;
	}

	public String getSsjczrq19() {
		return this.ssjczrq19;
	}

	public void setSsjczrq19(String ssjczrq19) {
		this.ssjczrq19 = ssjczrq19;
	}

	public String getSsjczrq2() {
		return this.ssjczrq2;
	}

	public void setSsjczrq2(String ssjczrq2) {
		this.ssjczrq2 = ssjczrq2;
	}

	public String getSsjczrq20() {
		return this.ssjczrq20;
	}

	public void setSsjczrq20(String ssjczrq20) {
		this.ssjczrq20 = ssjczrq20;
	}

	public String getSsjczrq21() {
		return this.ssjczrq21;
	}

	public void setSsjczrq21(String ssjczrq21) {
		this.ssjczrq21 = ssjczrq21;
	}

	public String getSsjczrq22() {
		return this.ssjczrq22;
	}

	public void setSsjczrq22(String ssjczrq22) {
		this.ssjczrq22 = ssjczrq22;
	}

	public String getSsjczrq23() {
		return this.ssjczrq23;
	}

	public void setSsjczrq23(String ssjczrq23) {
		this.ssjczrq23 = ssjczrq23;
	}

	public String getSsjczrq24() {
		return this.ssjczrq24;
	}

	public void setSsjczrq24(String ssjczrq24) {
		this.ssjczrq24 = ssjczrq24;
	}

	public String getSsjczrq25() {
		return this.ssjczrq25;
	}

	public void setSsjczrq25(String ssjczrq25) {
		this.ssjczrq25 = ssjczrq25;
	}

	public String getSsjczrq26() {
		return this.ssjczrq26;
	}

	public void setSsjczrq26(String ssjczrq26) {
		this.ssjczrq26 = ssjczrq26;
	}

	public String getSsjczrq27() {
		return this.ssjczrq27;
	}

	public void setSsjczrq27(String ssjczrq27) {
		this.ssjczrq27 = ssjczrq27;
	}

	public String getSsjczrq28() {
		return this.ssjczrq28;
	}

	public void setSsjczrq28(String ssjczrq28) {
		this.ssjczrq28 = ssjczrq28;
	}

	public String getSsjczrq29() {
		return this.ssjczrq29;
	}

	public void setSsjczrq29(String ssjczrq29) {
		this.ssjczrq29 = ssjczrq29;
	}

	public String getSsjczrq3() {
		return this.ssjczrq3;
	}

	public void setSsjczrq3(String ssjczrq3) {
		this.ssjczrq3 = ssjczrq3;
	}

	public String getSsjczrq30() {
		return this.ssjczrq30;
	}

	public void setSsjczrq30(String ssjczrq30) {
		this.ssjczrq30 = ssjczrq30;
	}

	public String getSsjczrq31() {
		return this.ssjczrq31;
	}

	public void setSsjczrq31(String ssjczrq31) {
		this.ssjczrq31 = ssjczrq31;
	}

	public String getSsjczrq32() {
		return this.ssjczrq32;
	}

	public void setSsjczrq32(String ssjczrq32) {
		this.ssjczrq32 = ssjczrq32;
	}

	public String getSsjczrq33() {
		return this.ssjczrq33;
	}

	public void setSsjczrq33(String ssjczrq33) {
		this.ssjczrq33 = ssjczrq33;
	}

	public String getSsjczrq34() {
		return this.ssjczrq34;
	}

	public void setSsjczrq34(String ssjczrq34) {
		this.ssjczrq34 = ssjczrq34;
	}

	public String getSsjczrq35() {
		return this.ssjczrq35;
	}

	public void setSsjczrq35(String ssjczrq35) {
		this.ssjczrq35 = ssjczrq35;
	}

	public String getSsjczrq36() {
		return this.ssjczrq36;
	}

	public void setSsjczrq36(String ssjczrq36) {
		this.ssjczrq36 = ssjczrq36;
	}

	public String getSsjczrq37() {
		return this.ssjczrq37;
	}

	public void setSsjczrq37(String ssjczrq37) {
		this.ssjczrq37 = ssjczrq37;
	}

	public String getSsjczrq38() {
		return this.ssjczrq38;
	}

	public void setSsjczrq38(String ssjczrq38) {
		this.ssjczrq38 = ssjczrq38;
	}

	public String getSsjczrq39() {
		return this.ssjczrq39;
	}

	public void setSsjczrq39(String ssjczrq39) {
		this.ssjczrq39 = ssjczrq39;
	}

	public String getSsjczrq4() {
		return this.ssjczrq4;
	}

	public void setSsjczrq4(String ssjczrq4) {
		this.ssjczrq4 = ssjczrq4;
	}

	public String getSsjczrq40() {
		return this.ssjczrq40;
	}

	public void setSsjczrq40(String ssjczrq40) {
		this.ssjczrq40 = ssjczrq40;
	}

	public String getSsjczrq41() {
		return this.ssjczrq41;
	}

	public void setSsjczrq41(String ssjczrq41) {
		this.ssjczrq41 = ssjczrq41;
	}

	public String getSsjczrq5() {
		return this.ssjczrq5;
	}

	public void setSsjczrq5(String ssjczrq5) {
		this.ssjczrq5 = ssjczrq5;
	}

	public String getSsjczrq6() {
		return this.ssjczrq6;
	}

	public void setSsjczrq6(String ssjczrq6) {
		this.ssjczrq6 = ssjczrq6;
	}

	public String getSsjczrq7() {
		return this.ssjczrq7;
	}

	public void setSsjczrq7(String ssjczrq7) {
		this.ssjczrq7 = ssjczrq7;
	}

	public String getSsjczrq8() {
		return this.ssjczrq8;
	}

	public void setSsjczrq8(String ssjczrq8) {
		this.ssjczrq8 = ssjczrq8;
	}

	public String getSsjczrq9() {
		return this.ssjczrq9;
	}

	public void setSsjczrq9(String ssjczrq9) {
		this.ssjczrq9 = ssjczrq9;
	}

	public String getSslclj() {
		return this.sslclj;
	}

	public void setSslclj(String sslclj) {
		this.sslclj = sslclj;
	}

	public Double getSsycxclf() {
		return this.ssycxclf == null ? 0 : this.ssycxclf;
	}

	public void setSsycxclf(Double ssycxclf) {
		this.ssycxclf = ssycxclf;
	}

	public Double getSszlf() {
		return this.sszlf == null ? 0 : this.sszlf;
	}

	public void setSszlf(Double sszlf) {
		this.sszlf = sszlf;
	}

	public String getSxys() {
		return this.sxys;
	}

	public void setSxys(String sxys) {
		this.sxys = sxys;
	}

	public String getSz1() {
		return this.sz1;
	}

	public void setSz1(String sz1) {
		this.sz1 = sz1;
	}

	public String getSz10() {
		return this.sz10;
	}

	public void setSz10(String sz10) {
		this.sz10 = sz10;
	}

	public String getSz11() {
		return this.sz11;
	}

	public void setSz11(String sz11) {
		this.sz11 = sz11;
	}

	public String getSz12() {
		return this.sz12;
	}

	public void setSz12(String sz12) {
		this.sz12 = sz12;
	}

	public String getSz13() {
		return this.sz13;
	}

	public void setSz13(String sz13) {
		this.sz13 = sz13;
	}

	public String getSz14() {
		return this.sz14;
	}

	public void setSz14(String sz14) {
		this.sz14 = sz14;
	}

	public String getSz15() {
		return this.sz15;
	}

	public void setSz15(String sz15) {
		this.sz15 = sz15;
	}

	public String getSz16() {
		return this.sz16;
	}

	public void setSz16(String sz16) {
		this.sz16 = sz16;
	}

	public String getSz17() {
		return this.sz17;
	}

	public void setSz17(String sz17) {
		this.sz17 = sz17;
	}

	public String getSz18() {
		return this.sz18;
	}

	public void setSz18(String sz18) {
		this.sz18 = sz18;
	}

	public String getSz19() {
		return this.sz19;
	}

	public void setSz19(String sz19) {
		this.sz19 = sz19;
	}

	public String getSz2() {
		return this.sz2;
	}

	public void setSz2(String sz2) {
		this.sz2 = sz2;
	}

	public String getSz20() {
		return this.sz20;
	}

	public void setSz20(String sz20) {
		this.sz20 = sz20;
	}

	public String getSz21() {
		return this.sz21;
	}

	public void setSz21(String sz21) {
		this.sz21 = sz21;
	}

	public String getSz22() {
		return this.sz22;
	}

	public void setSz22(String sz22) {
		this.sz22 = sz22;
	}

	public String getSz23() {
		return this.sz23;
	}

	public void setSz23(String sz23) {
		this.sz23 = sz23;
	}

	public String getSz24() {
		return this.sz24;
	}

	public void setSz24(String sz24) {
		this.sz24 = sz24;
	}

	public String getSz25() {
		return this.sz25;
	}

	public void setSz25(String sz25) {
		this.sz25 = sz25;
	}

	public String getSz26() {
		return this.sz26;
	}

	public void setSz26(String sz26) {
		this.sz26 = sz26;
	}

	public String getSz27() {
		return this.sz27;
	}

	public void setSz27(String sz27) {
		this.sz27 = sz27;
	}

	public String getSz28() {
		return this.sz28;
	}

	public void setSz28(String sz28) {
		this.sz28 = sz28;
	}

	public String getSz29() {
		return this.sz29;
	}

	public void setSz29(String sz29) {
		this.sz29 = sz29;
	}

	public String getSz3() {
		return this.sz3;
	}

	public void setSz3(String sz3) {
		this.sz3 = sz3;
	}

	public String getSz30() {
		return this.sz30;
	}

	public void setSz30(String sz30) {
		this.sz30 = sz30;
	}

	public String getSz31() {
		return this.sz31;
	}

	public void setSz31(String sz31) {
		this.sz31 = sz31;
	}

	public String getSz32() {
		return this.sz32;
	}

	public void setSz32(String sz32) {
		this.sz32 = sz32;
	}

	public String getSz33() {
		return this.sz33;
	}

	public void setSz33(String sz33) {
		this.sz33 = sz33;
	}

	public String getSz34() {
		return this.sz34;
	}

	public void setSz34(String sz34) {
		this.sz34 = sz34;
	}

	public String getSz35() {
		return this.sz35;
	}

	public void setSz35(String sz35) {
		this.sz35 = sz35;
	}

	public String getSz36() {
		return this.sz36;
	}

	public void setSz36(String sz36) {
		this.sz36 = sz36;
	}

	public String getSz37() {
		return this.sz37;
	}

	public void setSz37(String sz37) {
		this.sz37 = sz37;
	}

	public String getSz38() {
		return this.sz38;
	}

	public void setSz38(String sz38) {
		this.sz38 = sz38;
	}

	public String getSz39() {
		return this.sz39;
	}

	public void setSz39(String sz39) {
		this.sz39 = sz39;
	}

	public String getSz4() {
		return this.sz4;
	}

	public void setSz4(String sz4) {
		this.sz4 = sz4;
	}

	public String getSz40() {
		return this.sz40;
	}

	public void setSz40(String sz40) {
		this.sz40 = sz40;
	}

	public String getSz41() {
		return this.sz41;
	}

	public void setSz41(String sz41) {
		this.sz41 = sz41;
	}

	public String getSz5() {
		return this.sz5;
	}

	public void setSz5(String sz5) {
		this.sz5 = sz5;
	}

	public String getSz6() {
		return this.sz6;
	}

	public void setSz6(String sz6) {
		this.sz6 = sz6;
	}

	public String getSz7() {
		return this.sz7;
	}

	public void setSz7(String sz7) {
		this.sz7 = sz7;
	}

	public String getSz8() {
		return this.sz8;
	}

	public void setSz8(String sz8) {
		this.sz8 = sz8;
	}

	public String getSz9() {
		return this.sz9;
	}

	public void setSz9(String sz9) {
		this.sz9 = sz9;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getWbyy() {
		return this.wbyy;
	}

	public void setWbyy(String wbyy) {
		this.wbyy = wbyy;
	}

	@Column(name="wsy_jgmc")
	public String getWsyJgmc() {
		return this.wsyJgmc;
	}

	@Column(name="wsy_jgmc")
	public void setWsyJgmc(String wsyJgmc) {
		this.wsyJgmc = wsyJgmc;
	}

	public String getXb() {
		return this.xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public Double getXbyzlzpf() {
		return this.xbyzlzpf == null ? 0 : this.xbyzlzpf;
	}

	public void setXbyzlzpf(Double xbyzlzpf) {
		this.xbyzlzpf = xbyzlzpf;
	}

	public Double getXf() {
		return this.xf == null ? 0 : this.xf;
	}

	public void setXf(Double xf) {
		this.xf = xf;
	}

	public String getXm() {
		return this.xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXserytz() {
		return this.xserytz;
	}

	public void setXserytz(String xserytz) {
		this.xserytz = xserytz;
	}

	public String getXsetz() {
		return this.xsetz;
	}

	public void setXsetz(String xsetz) {
		this.xsetz = xsetz;
	}

	public String getXsetz2() {
		return this.xsetz2;
	}

	public void setXsetz2(String xsetz2) {
		this.xsetz2 = xsetz2;
	}

	public String getXsetz3() {
		return this.xsetz3;
	}

	public void setXsetz3(String xsetz3) {
		this.xsetz3 = xsetz3;
	}

	public String getXsetz4() {
		return this.xsetz4;
	}

	public void setXsetz4(String xsetz4) {
		this.xsetz4 = xsetz4;
	}

	public String getXsetz5() {
		return this.xsetz5;
	}

	public void setXsetz5(String xsetz5) {
		this.xsetz5 = xsetz5;
	}

	public String getXx() {
		return this.xx;
	}

	public void setXx(String xx) {
		this.xx = xx;
	}

	@Column(name="xy_rybq")
	public String getXyRybq() {
		return this.xyRybq;
	}

	@Column(name="xy_rybq")
	public void setXyRybq(String xyRybq) {
		this.xyRybq = xyRybq;
	}

	public Double getXyf() {
		return this.xyf == null ? 0 : this.xyf;
	}

	public void setXyf(Double xyf) {
		this.xyf = xyf;
	}

	public String getXzz() {
		return this.xzz;
	}

	public void setXzz(String xzz) {
		this.xzz = xzz;
	}

	public String getYb1() {
		return this.yb1;
	}

	public void setYb1(String yb1) {
		this.yb1 = yb1;
	}

	public String getYb2() {
		return this.yb2;
	}

	public void setYb2(String yb2) {
		this.yb2 = yb2;
	}

	public String getYb3() {
		return this.yb3;
	}

	public void setYb3(String yb3) {
		this.yb3 = yb3;
	}

	public String getYlfkfs() {
		return this.ylfkfs;
	}

	public void setYlfkfs(String ylfkfs) {
		this.ylfkfs = ylfkfs;
	}

	public Double getYlfwf() {
		return this.ylfwf == null ? 0 : this.ylfwf;
	}

	public void setYlfwf(Double ylfwf) {
		this.ylfwf = ylfwf;
	}

	public String getYwgm() {
		return this.ywgm;
	}

	public void setYwgm(String ywgm) {
		this.ywgm = ywgm;
	}

	public Double getYxxzdf() {
		return this.yxxzdf == null ? 0 : this.yxxzdf;
	}

	public void setYxxzdf(Double yxxzdf) {
		this.yxxzdf = yxxzdf;
	}

	public Double getYyclf() {
		return this.yyclf == null ? 0 : this.yyclf;
	}

	public void setYyclf(Double yyclf) {
		this.yyclf = yyclf;
	}

	public String getYz1() {
		return this.yz1;
	}

	public void setYz1(String yz1) {
		this.yz1 = yz1;
	}

	public String getYz10() {
		return this.yz10;
	}

	public void setYz10(String yz10) {
		this.yz10 = yz10;
	}

	public String getYz11() {
		return this.yz11;
	}

	public void setYz11(String yz11) {
		this.yz11 = yz11;
	}

	public String getYz12() {
		return this.yz12;
	}

	public void setYz12(String yz12) {
		this.yz12 = yz12;
	}

	public String getYz13() {
		return this.yz13;
	}

	public void setYz13(String yz13) {
		this.yz13 = yz13;
	}

	public String getYz14() {
		return this.yz14;
	}

	public void setYz14(String yz14) {
		this.yz14 = yz14;
	}

	public String getYz15() {
		return this.yz15;
	}

	public void setYz15(String yz15) {
		this.yz15 = yz15;
	}

	public String getYz16() {
		return this.yz16;
	}

	public void setYz16(String yz16) {
		this.yz16 = yz16;
	}

	public String getYz17() {
		return this.yz17;
	}

	public void setYz17(String yz17) {
		this.yz17 = yz17;
	}

	public String getYz18() {
		return this.yz18;
	}

	public void setYz18(String yz18) {
		this.yz18 = yz18;
	}

	public String getYz19() {
		return this.yz19;
	}

	public void setYz19(String yz19) {
		this.yz19 = yz19;
	}

	public String getYz2() {
		return this.yz2;
	}

	public void setYz2(String yz2) {
		this.yz2 = yz2;
	}

	public String getYz20() {
		return this.yz20;
	}

	public void setYz20(String yz20) {
		this.yz20 = yz20;
	}

	public String getYz21() {
		return this.yz21;
	}

	public void setYz21(String yz21) {
		this.yz21 = yz21;
	}

	public String getYz22() {
		return this.yz22;
	}

	public void setYz22(String yz22) {
		this.yz22 = yz22;
	}

	public String getYz23() {
		return this.yz23;
	}

	public void setYz23(String yz23) {
		this.yz23 = yz23;
	}

	public String getYz24() {
		return this.yz24;
	}

	public void setYz24(String yz24) {
		this.yz24 = yz24;
	}

	public String getYz25() {
		return this.yz25;
	}

	public void setYz25(String yz25) {
		this.yz25 = yz25;
	}

	public String getYz26() {
		return this.yz26;
	}

	public void setYz26(String yz26) {
		this.yz26 = yz26;
	}

	public String getYz27() {
		return this.yz27;
	}

	public void setYz27(String yz27) {
		this.yz27 = yz27;
	}

	public String getYz28() {
		return this.yz28;
	}

	public void setYz28(String yz28) {
		this.yz28 = yz28;
	}

	public String getYz29() {
		return this.yz29;
	}

	public void setYz29(String yz29) {
		this.yz29 = yz29;
	}

	public String getYz3() {
		return this.yz3;
	}

	public void setYz3(String yz3) {
		this.yz3 = yz3;
	}

	public String getYz30() {
		return this.yz30;
	}

	public void setYz30(String yz30) {
		this.yz30 = yz30;
	}

	public String getYz31() {
		return this.yz31;
	}

	public void setYz31(String yz31) {
		this.yz31 = yz31;
	}

	public String getYz32() {
		return this.yz32;
	}

	public void setYz32(String yz32) {
		this.yz32 = yz32;
	}

	public String getYz33() {
		return this.yz33;
	}

	public void setYz33(String yz33) {
		this.yz33 = yz33;
	}

	public String getYz34() {
		return this.yz34;
	}

	public void setYz34(String yz34) {
		this.yz34 = yz34;
	}

	public String getYz35() {
		return this.yz35;
	}

	public void setYz35(String yz35) {
		this.yz35 = yz35;
	}

	public String getYz36() {
		return this.yz36;
	}

	public void setYz36(String yz36) {
		this.yz36 = yz36;
	}

	public String getYz37() {
		return this.yz37;
	}

	public void setYz37(String yz37) {
		this.yz37 = yz37;
	}

	public String getYz38() {
		return this.yz38;
	}

	public void setYz38(String yz38) {
		this.yz38 = yz38;
	}

	public String getYz39() {
		return this.yz39;
	}

	public void setYz39(String yz39) {
		this.yz39 = yz39;
	}

	public String getYz4() {
		return this.yz4;
	}

	public void setYz4(String yz4) {
		this.yz4 = yz4;
	}

	public String getYz40() {
		return this.yz40;
	}

	public void setYz40(String yz40) {
		this.yz40 = yz40;
	}

	public String getYz41() {
		return this.yz41;
	}

	public void setYz41(String yz41) {
		this.yz41 = yz41;
	}

	public String getYz5() {
		return this.yz5;
	}

	public void setYz5(String yz5) {
		this.yz5 = yz5;
	}

	public String getYz6() {
		return this.yz6;
	}

	public void setYz6(String yz6) {
		this.yz6 = yz6;
	}

	public String getYz7() {
		return this.yz7;
	}

	public void setYz7(String yz7) {
		this.yz7 = yz7;
	}

	public String getYz8() {
		return this.yz8;
	}

	public void setYz8(String yz8) {
		this.yz8 = yz8;
	}

	public String getYz9() {
		return this.yz9;
	}

	public void setYz9(String yz9) {
		this.yz9 = yz9;
	}

	@Column(name="yzzy_jgmc")
	public String getYzzyJgmc() {
		return this.yzzyJgmc;
	}

	@Column(name="yzzy_jgmc")
	public void setYzzyJgmc(String yzzyJgmc) {
		this.yzzyJgmc = yzzyJgmc;
	}

	public String getZb() {
		return this.zb;
	}

	public void setZb(String zb) {
		this.zb = zb;
	}

	@Column(name="zb_jbbm")
	public String getZbJbbm() {
		return this.zbJbbm;
	}

	@Column(name="zb_jbbm")
	public void setZbJbbm(String zbJbbm) {
		this.zbJbbm = zbJbbm;
	}

	@Column(name="zb_rybq")
	public String getZbRybq() {
		return this.zbRybq;
	}

	@Column(name="zb_rybq")
	public void setZbRybq(String zbRybq) {
		this.zbRybq = zbRybq;
	}

	public Double getZcyf() {
		return this.zcyf == null ? 0 : this.zcyf;
	}

	public void setZcyf(Double zcyf) {
		this.zcyf = zcyf;
	}

	public Double getZcyf1() {
		return this.zcyf1 == null ? 0 : this.zcyf1;
	}

	public void setZcyf1(Double zcyf1) {
		this.zcyf1 = zcyf1;
	}

	public Double getZcyjf() {
		return this.zcyjf == null ? 0 : this.zcyjf;
	}

	public void setZcyjf(Double zcyjf) {
		this.zcyjf = zcyjf;
	}

	public Double getZdf() {
		return this.zdf == null ? 0 : this.zdf;
	}

	public void setZdf(Double zdf) {
		this.zdf = zdf;
	}

	public Double getZfje() {
		return this.zfje == null ? 0 : this.zfje;
	}

	public void setZfje(Double zfje) {
		this.zfje = zfje;
	}

	public Double getZfy() {
		return this.zfy == null ? 0 : this.zfy;
	}

	public void setZfy(Double zfy) {
		this.zfy = zfy;
	}

	public String getZkhs() {
		return this.zkhs;
	}

	public void setZkhs(String zkhs) {
		this.zkhs = zkhs;
	}

	public String getZkkb() {
		return this.zkkb;
	}

	public void setZkkb(String zkkb) {
		this.zkkb = zkkb;
	}

	public String getZkrq() {
		return this.zkrq;
	}

	public void setZkrq(String zkrq) {
		this.zkrq = zkrq;
	}

	public String getZkys() {
		return this.zkys;
	}

	public void setZkys(String zkys) {
		this.zkys = zkys;
	}

	public Double getZlczf() {
		return this.zlczf == null ? 0 : this.zlczf;
	}

	public void setZlczf(Double zlczf) {
		this.zlczf = zlczf;
	}

	public Double getZlf() {
		return this.zlf == null ? 0 : this.zlf;
	}

	public void setZlf(Double zlf) {
		this.zlf = zlf;
	}

	public String getZllb() {
		return this.zllb;
	}

	public void setZllb(String zllb) {
		this.zllb = zllb;
	}

	public String getZrhs() {
		return this.zrhs;
	}

	public void setZrhs(String zrhs) {
		this.zrhs = zrhs;
	}

	public String getZrys() {
		return this.zrys;
	}

	public void setZrys(String zrys) {
		this.zrys = zrys;
	}

	public String getZy() {
		return this.zy;
	}

	public void setZy(String zy) {
		this.zy = zy;
	}

	public Double getZyblzhzf() {
		return this.zyblzhzf == null ? 0 : this.zyblzhzf;
	}

	public void setZyblzhzf(Double zyblzhzf) {
		this.zyblzhzf = zyblzhzf;
	}

	public String getZycs() {
		return this.zycs;
	}

	public void setZycs(String zycs) {
		this.zycs = zycs;
	}

	public Double getZygczl() {
		return this.zygczl == null ? 0 : this.zygczl;
	}

	public void setZygczl(Double zygczl) {
		this.zygczl = zygczl;
	}

	public Double getZygs() {
		return this.zygs == null ? 0 : this.zygs;
	}

	public void setZygs(Double zygs) {
		this.zygs = zygs;
	}

	@Column(name="zyl_zyzd")
	public Double getZylZyzd() {
		return this.zylZyzd == null ? 0 : this.zylZyzd;
	}

	@Column(name="zyl_zyzd")
	public void setZylZyzd(Double zylZyzd) {
		this.zylZyzd = zylZyzd;
	}

	public Double getZyqt() {
		return this.zyqt == null ? 0 : this.zyqt;
	}

	public void setZyqt(Double zyqt) {
		this.zyqt = zyqt;
	}

	public Double getZytnzl() {
		return this.zytnzl == null ? 0 : this.zytnzl;
	}

	public void setZytnzl(Double zytnzl) {
		this.zytnzl = zytnzl;
	}

	public Double getZytstpjg() {
		return this.zytstpjg == null ? 0 : this.zytstpjg;
	}

	public void setZytstpjg(Double zytstpjg) {
		this.zytstpjg = zytstpjg;
	}

	public Double getZytszl() {
		return this.zytszl == null ? 0 : this.zytszl;
	}

	public void setZytszl(Double zytszl) {
		this.zytszl = zytszl;
	}

	public Double getZywz() {
		return this.zywz == null ? 0 : this.zywz;
	}

	public void setZywz(Double zywz) {
		this.zywz = zywz;
	}

	public String getZyyj() {
		return this.zyyj;
	}

	public void setZyyj(String zyyj) {
		this.zyyj = zyyj;
	}

	public String getZyys() {
		return this.zyys;
	}

	public void setZyys(String zyys) {
		this.zyys = zyys;
	}

	public String getZyzd() {
		return this.zyzd;
	}

	public void setZyzd(String zyzd) {
		this.zyzd = zyzd;
	}

	@Column(name="zyzd_jbbm")
	public String getZyzdJbbm() {
		return this.zyzdJbbm;
	}

	@Column(name="zyzd_jbbm")
	public void setZyzdJbbm(String zyzdJbbm) {
		this.zyzdJbbm = zyzdJbbm;
	}

	@Column(name="zyzd_jbbm1")
	public String getZyzdJbbm1() {
		return this.zyzdJbbm1;
	}

	@Column(name="zyzd_jbbm1")
	public void setZyzdJbbm1(String zyzdJbbm1) {
		this.zyzdJbbm1 = zyzdJbbm1;
	}

	@Column(name="zyzd_jbbm10")
	public String getZyzdJbbm10() {
		return this.zyzdJbbm10;
	}

	@Column(name="zyzd_jbbm10")
	public void setZyzdJbbm10(String zyzdJbbm10) {
		this.zyzdJbbm10 = zyzdJbbm10;
	}

	@Column(name="zyzd_jbbm11")
	public String getZyzdJbbm11() {
		return this.zyzdJbbm11;
	}

	@Column(name="zyzd_jbbm11")
	public void setZyzdJbbm11(String zyzdJbbm11) {
		this.zyzdJbbm11 = zyzdJbbm11;
	}

	@Column(name="zyzd_jbbm12")
	public String getZyzdJbbm12() {
		return this.zyzdJbbm12;
	}

	@Column(name="zyzd_jbbm12")
	public void setZyzdJbbm12(String zyzdJbbm12) {
		this.zyzdJbbm12 = zyzdJbbm12;
	}

	@Column(name="zyzd_jbbm13")
	public String getZyzdJbbm13() {
		return this.zyzdJbbm13;
	}

	@Column(name="zyzd_jbbm13")
	public void setZyzdJbbm13(String zyzdJbbm13) {
		this.zyzdJbbm13 = zyzdJbbm13;
	}

	@Column(name="zyzd_jbbm14")
	public String getZyzdJbbm14() {
		return this.zyzdJbbm14;
	}

	@Column(name="zyzd_jbbm14")
	public void setZyzdJbbm14(String zyzdJbbm14) {
		this.zyzdJbbm14 = zyzdJbbm14;
	}

	@Column(name="zyzd_jbbm15")
	public String getZyzdJbbm15() {
		return this.zyzdJbbm15;
	}

	@Column(name="zyzd_jbbm15")
	public void setZyzdJbbm15(String zyzdJbbm15) {
		this.zyzdJbbm15 = zyzdJbbm15;
	}

	@Column(name="zyzd_jbbm16")
	public String getZyzdJbbm16() {
		return this.zyzdJbbm16;
	}

	@Column(name="zyzd_jbbm16")
	public void setZyzdJbbm16(String zyzdJbbm16) {
		this.zyzdJbbm16 = zyzdJbbm16;
	}

	@Column(name="zyzd_jbbm17")
	public String getZyzdJbbm17() {
		return this.zyzdJbbm17;
	}

	@Column(name="zyzd_jbbm17")
	public void setZyzdJbbm17(String zyzdJbbm17) {
		this.zyzdJbbm17 = zyzdJbbm17;
	}

	@Column(name="zyzd_jbbm18")
	public String getZyzdJbbm18() {
		return this.zyzdJbbm18;
	}

	@Column(name="zyzd_jbbm18")
	public void setZyzdJbbm18(String zyzdJbbm18) {
		this.zyzdJbbm18 = zyzdJbbm18;
	}

	@Column(name="zyzd_jbbm19")
	public String getZyzdJbbm19() {
		return this.zyzdJbbm19;
	}

	@Column(name="zyzd_jbbm19")
	public void setZyzdJbbm19(String zyzdJbbm19) {
		this.zyzdJbbm19 = zyzdJbbm19;
	}

	@Column(name="zyzd_jbbm2")
	public String getZyzdJbbm2() {
		return this.zyzdJbbm2;
	}

	@Column(name="zyzd_jbbm2")
	public void setZyzdJbbm2(String zyzdJbbm2) {
		this.zyzdJbbm2 = zyzdJbbm2;
	}

	@Column(name="zyzd_jbbm20")
	public String getZyzdJbbm20() {
		return this.zyzdJbbm20;
	}

	@Column(name="zyzd_jbbm20")
	public void setZyzdJbbm20(String zyzdJbbm20) {
		this.zyzdJbbm20 = zyzdJbbm20;
	}

	@Column(name="zyzd_jbbm21")
	public String getZyzdJbbm21() {
		return this.zyzdJbbm21;
	}

	@Column(name="zyzd_jbbm21")
	public void setZyzdJbbm21(String zyzdJbbm21) {
		this.zyzdJbbm21 = zyzdJbbm21;
	}

	@Column(name="zyzd_jbbm22")
	public String getZyzdJbbm22() {
		return this.zyzdJbbm22;
	}

	@Column(name="zyzd_jbbm22")
	public void setZyzdJbbm22(String zyzdJbbm22) {
		this.zyzdJbbm22 = zyzdJbbm22;
	}

	@Column(name="zyzd_jbbm23")
	public String getZyzdJbbm23() {
		return this.zyzdJbbm23;
	}

	@Column(name="zyzd_jbbm23")
	public void setZyzdJbbm23(String zyzdJbbm23) {
		this.zyzdJbbm23 = zyzdJbbm23;
	}

	@Column(name="zyzd_jbbm24")
	public String getZyzdJbbm24() {
		return this.zyzdJbbm24;
	}

	@Column(name="zyzd_jbbm24")
	public void setZyzdJbbm24(String zyzdJbbm24) {
		this.zyzdJbbm24 = zyzdJbbm24;
	}

	@Column(name="zyzd_jbbm25")
	public String getZyzdJbbm25() {
		return this.zyzdJbbm25;
	}

	@Column(name="zyzd_jbbm25")
	public void setZyzdJbbm25(String zyzdJbbm25) {
		this.zyzdJbbm25 = zyzdJbbm25;
	}

	@Column(name="zyzd_jbbm26")
	public String getZyzdJbbm26() {
		return this.zyzdJbbm26;
	}

	@Column(name="zyzd_jbbm26")
	public void setZyzdJbbm26(String zyzdJbbm26) {
		this.zyzdJbbm26 = zyzdJbbm26;
	}

	@Column(name="zyzd_jbbm27")
	public String getZyzdJbbm27() {
		return this.zyzdJbbm27;
	}

	@Column(name="zyzd_jbbm27")
	public void setZyzdJbbm27(String zyzdJbbm27) {
		this.zyzdJbbm27 = zyzdJbbm27;
	}

	@Column(name="zyzd_jbbm28")
	public String getZyzdJbbm28() {
		return this.zyzdJbbm28;
	}

	@Column(name="zyzd_jbbm28")
	public void setZyzdJbbm28(String zyzdJbbm28) {
		this.zyzdJbbm28 = zyzdJbbm28;
	}

	@Column(name="zyzd_jbbm29")
	public String getZyzdJbbm29() {
		return this.zyzdJbbm29;
	}

	@Column(name="zyzd_jbbm29")
	public void setZyzdJbbm29(String zyzdJbbm29) {
		this.zyzdJbbm29 = zyzdJbbm29;
	}

	@Column(name="zyzd_jbbm3")
	public String getZyzdJbbm3() {
		return this.zyzdJbbm3;
	}

	@Column(name="zyzd_jbbm3")
	public void setZyzdJbbm3(String zyzdJbbm3) {
		this.zyzdJbbm3 = zyzdJbbm3;
	}

	@Column(name="zyzd_jbbm30")
	public String getZyzdJbbm30() {
		return this.zyzdJbbm30;
	}

	@Column(name="zyzd_jbbm30")
	public void setZyzdJbbm30(String zyzdJbbm30) {
		this.zyzdJbbm30 = zyzdJbbm30;
	}

	@Column(name="zyzd_jbbm31")
	public String getZyzdJbbm31() {
		return this.zyzdJbbm31;
	}

	@Column(name="zyzd_jbbm31")
	public void setZyzdJbbm31(String zyzdJbbm31) {
		this.zyzdJbbm31 = zyzdJbbm31;
	}

	@Column(name="zyzd_jbbm32")
	public String getZyzdJbbm32() {
		return this.zyzdJbbm32;
	}

	@Column(name="zyzd_jbbm32")
	public void setZyzdJbbm32(String zyzdJbbm32) {
		this.zyzdJbbm32 = zyzdJbbm32;
	}

	@Column(name="zyzd_jbbm33")
	public String getZyzdJbbm33() {
		return this.zyzdJbbm33;
	}

	@Column(name="zyzd_jbbm33")
	public void setZyzdJbbm33(String zyzdJbbm33) {
		this.zyzdJbbm33 = zyzdJbbm33;
	}

	@Column(name="zyzd_jbbm34")
	public String getZyzdJbbm34() {
		return this.zyzdJbbm34;
	}

	@Column(name="zyzd_jbbm34")
	public void setZyzdJbbm34(String zyzdJbbm34) {
		this.zyzdJbbm34 = zyzdJbbm34;
	}

	@Column(name="zyzd_jbbm35")
	public String getZyzdJbbm35() {
		return this.zyzdJbbm35;
	}

	@Column(name="zyzd_jbbm35")
	public void setZyzdJbbm35(String zyzdJbbm35) {
		this.zyzdJbbm35 = zyzdJbbm35;
	}

	@Column(name="zyzd_jbbm36")
	public String getZyzdJbbm36() {
		return this.zyzdJbbm36;
	}

	@Column(name="zyzd_jbbm36")
	public void setZyzdJbbm36(String zyzdJbbm36) {
		this.zyzdJbbm36 = zyzdJbbm36;
	}

	@Column(name="zyzd_jbbm37")
	public String getZyzdJbbm37() {
		return this.zyzdJbbm37;
	}

	@Column(name="zyzd_jbbm37")
	public void setZyzdJbbm37(String zyzdJbbm37) {
		this.zyzdJbbm37 = zyzdJbbm37;
	}

	@Column(name="zyzd_jbbm38")
	public String getZyzdJbbm38() {
		return this.zyzdJbbm38;
	}

	@Column(name="zyzd_jbbm38")
	public void setZyzdJbbm38(String zyzdJbbm38) {
		this.zyzdJbbm38 = zyzdJbbm38;
	}

	@Column(name="zyzd_jbbm39")
	public String getZyzdJbbm39() {
		return this.zyzdJbbm39;
	}

	@Column(name="zyzd_jbbm39")
	public void setZyzdJbbm39(String zyzdJbbm39) {
		this.zyzdJbbm39 = zyzdJbbm39;
	}

	@Column(name="zyzd_jbbm4")
	public String getZyzdJbbm4() {
		return this.zyzdJbbm4;
	}

	@Column(name="zyzd_jbbm4")
	public void setZyzdJbbm4(String zyzdJbbm4) {
		this.zyzdJbbm4 = zyzdJbbm4;
	}

	@Column(name="zyzd_jbbm40")
	public String getZyzdJbbm40() {
		return this.zyzdJbbm40;
	}

	@Column(name="zyzd_jbbm40")
	public void setZyzdJbbm40(String zyzdJbbm40) {
		this.zyzdJbbm40 = zyzdJbbm40;
	}

	@Column(name="zyzd_jbbm5")
	public String getZyzdJbbm5() {
		return this.zyzdJbbm5;
	}

	@Column(name="zyzd_jbbm5")
	public void setZyzdJbbm5(String zyzdJbbm5) {
		this.zyzdJbbm5 = zyzdJbbm5;
	}

	@Column(name="zyzd_jbbm6")
	public String getZyzdJbbm6() {
		return this.zyzdJbbm6;
	}

	@Column(name="zyzd_jbbm6")
	public void setZyzdJbbm6(String zyzdJbbm6) {
		this.zyzdJbbm6 = zyzdJbbm6;
	}

	@Column(name="zyzd_jbbm7")
	public String getZyzdJbbm7() {
		return this.zyzdJbbm7;
	}

	@Column(name="zyzd_jbbm7")
	public void setZyzdJbbm7(String zyzdJbbm7) {
		this.zyzdJbbm7 = zyzdJbbm7;
	}

	@Column(name="zyzd_jbbm8")
	public String getZyzdJbbm8() {
		return this.zyzdJbbm8;
	}

	@Column(name="zyzd_jbbm8")
	public void setZyzdJbbm8(String zyzdJbbm8) {
		this.zyzdJbbm8 = zyzdJbbm8;
	}

	@Column(name="zyzd_jbbm9")
	public String getZyzdJbbm9() {
		return this.zyzdJbbm9;
	}

	@Column(name="zyzd_jbbm9")
	public void setZyzdJbbm9(String zyzdJbbm9) {
		this.zyzdJbbm9 = zyzdJbbm9;
	}

	public Double getZyzjf() {
		return this.zyzjf == null ? 0 : this.zyzjf;
	}

	public void setZyzjf(Double zyzjf) {
		this.zyzjf = zyzjf;
	}

	public Double getZyzl() {
		return this.zyzl == null ? 0 : this.zyzl;
	}

	public void setZyzl(Double zyzl) {
		this.zyzl = zyzl;
	}

	public String getZyzljs() {
		return this.zyzljs;
	}

	public void setZyzljs(String zyzljs) {
		this.zyzljs = zyzljs;
	}

	public String getZyzlsb() {
		return this.zyzlsb;
	}

	public void setZyzlsb(String zyzlsb) {
		this.zyzlsb = zyzlsb;
	}

	@Column(name="zz_jbbm1")
	public String getZzJbbm1() {
		return this.zzJbbm1;
	}

	@Column(name="zz_jbbm1")
	public void setZzJbbm1(String zzJbbm1) {
		this.zzJbbm1 = zzJbbm1;
	}

	@Column(name="zz_jbbm2")
	public String getZzJbbm2() {
		return this.zzJbbm2;
	}

	@Column(name="zz_jbbm2")
	public void setZzJbbm2(String zzJbbm2) {
		this.zzJbbm2 = zzJbbm2;
	}

	@Column(name="zz_jbbm3")
	public String getZzJbbm3() {
		return this.zzJbbm3;
	}

	@Column(name="zz_jbbm3")
	public void setZzJbbm3(String zzJbbm3) {
		this.zzJbbm3 = zzJbbm3;
	}

	@Column(name="zz_jbbm4")
	public String getZzJbbm4() {
		return this.zzJbbm4;
	}

	@Column(name="zz_jbbm4")
	public void setZzJbbm4(String zzJbbm4) {
		this.zzJbbm4 = zzJbbm4;
	}

	@Column(name="zz_jbbm5")
	public String getZzJbbm5() {
		return this.zzJbbm5;
	}

	@Column(name="zz_jbbm5")
	public void setZzJbbm5(String zzJbbm5) {
		this.zzJbbm5 = zzJbbm5;
	}

	@Column(name="zz_jbbm6")
	public String getZzJbbm6() {
		return this.zzJbbm6;
	}

	@Column(name="zz_jbbm6")
	public void setZzJbbm6(String zzJbbm6) {
		this.zzJbbm6 = zzJbbm6;
	}

	@Column(name="zz_jbbm7")
	public String getZzJbbm7() {
		return this.zzJbbm7;
	}

	@Column(name="zz_jbbm7")
	public void setZzJbbm7(String zzJbbm7) {
		this.zzJbbm7 = zzJbbm7;
	}

	@Column(name="zz_rybq1")
	public String getZzRybq1() {
		return this.zzRybq1;
	}

	@Column(name="zz_rybq1")
	public void setZzRybq1(String zzRybq1) {
		this.zzRybq1 = zzRybq1;
	}

	@Column(name="zz_rybq2")
	public String getZzRybq2() {
		return this.zzRybq2;
	}

	@Column(name="zz_rybq2")
	public void setZzRybq2(String zzRybq2) {
		this.zzRybq2 = zzRybq2;
	}

	@Column(name="zz_rybq3")
	public String getZzRybq3() {
		return this.zzRybq3;
	}

	@Column(name="zz_rybq3")
	public void setZzRybq3(String zzRybq3) {
		this.zzRybq3 = zzRybq3;
	}

	@Column(name="zz_rybq4")
	public String getZzRybq4() {
		return this.zzRybq4;
	}

	@Column(name="zz_rybq4")
	public void setZzRybq4(String zzRybq4) {
		this.zzRybq4 = zzRybq4;
	}

	@Column(name="zz_rybq5")
	public String getZzRybq5() {
		return this.zzRybq5;
	}

	@Column(name="zz_rybq5")
	public void setZzRybq5(String zzRybq5) {
		this.zzRybq5 = zzRybq5;
	}

	@Column(name="zz_rybq6")
	public String getZzRybq6() {
		return this.zzRybq6;
	}

	@Column(name="zz_rybq6")
	public void setZzRybq6(String zzRybq6) {
		this.zzRybq6 = zzRybq6;
	}

	@Column(name="zz_rybq7")
	public String getZzRybq7() {
		return this.zzRybq7;
	}

	@Column(name="zz_rybq7")
	public void setZzRybq7(String zzRybq7) {
		this.zzRybq7 = zzRybq7;
	}

	public String getZz1() {
		return this.zz1;
	}

	public void setZz1(String zz1) {
		this.zz1 = zz1;
	}

	public String getZz2() {
		return this.zz2;
	}

	public void setZz2(String zz2) {
		this.zz2 = zz2;
	}

	public String getZz3() {
		return this.zz3;
	}

	public void setZz3(String zz3) {
		this.zz3 = zz3;
	}

	public String getZz4() {
		return this.zz4;
	}

	public void setZz4(String zz4) {
		this.zz4 = zz4;
	}

	public String getZz5() {
		return this.zz5;
	}

	public void setZz5(String zz5) {
		this.zz5 = zz5;
	}

	public String getZz6() {
		return this.zz6;
	}

	public void setZz6(String zz6) {
		this.zz6 = zz6;
	}

	public String getZz7() {
		return this.zz7;
	}

	public void setZz7(String zz7) {
		this.zz7 = zz7;
	}

	public String getZzjgdm() {
		return this.zzjgdm;
	}

	public void setZzjgdm(String zzjgdm) {
		this.zzjgdm = zzjgdm;
	}

	public String getZzyjh() {
		return this.zzyjh;
	}

	public void setZzyjh(String zzyjh) {
		this.zzyjh = zzyjh;
	}

	public String getZzys() {
		return this.zzys;
	}

	public void setZzys(String zzys) {
		this.zzys = zzys;
	}

}