/*
 * package com.cg.osm.entity;
 * 
 * import java.time.LocalDate; import java.util.List; import java.util.Map;
 * 
 * import javax.persistence.CascadeType; import javax.persistence.Column; import
 * javax.persistence.ElementCollection; import javax.persistence.Entity; import
 * javax.persistence.FetchType; import javax.persistence.GeneratedValue; import
 * javax.persistence.GenerationType; import javax.persistence.Id; import
 * javax.persistence.ManyToOne;
 * 
 * import org.hibernate.annotations.CollectionId;
 * 
 * @Entity public class SweetOrder {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer
 * sweetOrderId;
 * 
 * @ManyToOne private Customer customer;
 * 
 * @ElementCollection(targetClass = Product.class) private List<Product>
 * listItems;
 * 
 * @Column(name = "OrderDate") private LocalDate createdDate;
 * 
 * @Column(name = "Total") private double total;
 * 
 * public SweetOrder() { }
 * 
 * public SweetOrder(Integer sweetOrderId, Customer customer, List<Product>
 * listItems, LocalDate createdDate, double total) { this.sweetOrderId =
 * sweetOrderId; this.customer = customer; this.listItems = listItems;
 * this.createdDate = createdDate; this.total = total; }
 * 
 * public Integer getSweetOrderId() { return sweetOrderId; }
 * 
 * public void setSweetOrderId(Integer sweetOrderId) { this.sweetOrderId =
 * sweetOrderId; }
 * 
 * public Customer getCustomer() { return customer; }
 * 
 * public void setCustomer(Customer customer) { this.customer = customer; }
 * 
 * public List<Product> getListItems() { return listItems; }
 * 
 * public void setListItems(List<Product> listItems) { this.listItems =
 * listItems; }
 * 
 * public LocalDate getCreatedDate() { return createdDate; }
 * 
 * public void setCreatedDate(LocalDate createdDate) { this.createdDate =
 * createdDate; }
 * 
 * public double getTotal() { return total; }
 * 
 * public void setTotal(double total) { this.total = total; }
 * 
 * @Override public String toString() { return "SweetOrder [sweetOrderId=" +
 * sweetOrderId + ", customer=" + customer + ", listItems=" + listItems +
 * ", createdDate=" + createdDate + ", total=" + total + "]"; }
 * 
 * }
 */

package com.cg.osm.entity;
import java.time.LocalDate;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class SweetOrder {
    @Id
  //  @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer orderId;
    @Column(name="TotalCost")
	private double totalCost;
    @Column(name="Date")
	private LocalDate date;
    @JsonBackReference
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name="customer_userId")
    private Customer customer;
    @ManyToMany
    @JoinTable(name = "Orders_Product",
    		        joinColumns = { @JoinColumn(name = "sweet_order_orderid") }, 
    		        inverseJoinColumns = { @JoinColumn(name = "prod_list_prodid") })
    //@JsonBackReference
    private List<Product> prodList;
	
    public SweetOrder() {
		
	}

	public SweetOrder(int orderId,List<Product> prodList) {
		this.orderId = orderId;
		//this.totalCost = totalCost;
		//this.date = date;
//		this.customerId=customerId;
		//this.customer = customer;
		this.prodList = prodList;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Product> getProdList() {
		return prodList;
	}

	public void setProdList(List<Product> prodList) {
		this.prodList = prodList;
	}

	@Override
	public String toString() {
		return "SweetOrder [orderId=" + orderId + ", totalCost=" + totalCost + ", date=" + date + /*",customerId="+customerId+
			*/", customer=" + customer + ", prodList=" + prodList + "]";
	}
	
	
	
}