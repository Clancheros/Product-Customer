package customer.repositories;

import customer.entities.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CustomerRepository {
    @Inject
    EntityManager em;

    @Transactional
    public void createdCustomer(Customer c){
        em.persist(c);
    }

    @Transactional
    public void deleteCustomer(Customer c){
        em.remove(em.contains(c) ? c : em.merge(c));
    }

    @Transactional
    public List<Customer> listCustomer(){
        List<Customer> customers = em.createQuery("select c from Customer c").getResultList();
        return customers;
    }
    @Transactional
    public Customer findCustomer(Long Id){
        return em.find(Customer.class, Id);
    }
    @Transactional
    public void updateCustomer(Customer c){
        em.merge(c);
    }
}
