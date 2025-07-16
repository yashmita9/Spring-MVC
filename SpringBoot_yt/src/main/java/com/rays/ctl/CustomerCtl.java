package com.rays.ctl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.CustomerDTO;
import com.rays.form.CustomerForm;
import com.rays.service.CustomerService;

@RestController
@RequestMapping(value = "Customer")
public class CustomerCtl extends BaseCtl{
	
	@Autowired
	public CustomerService customerService;
	
	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid CustomerForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		CustomerDTO dto = (CustomerDTO) form.getDto();

		if (dto.getId() != null && dto.getId() > 0) {
			customerService.update(dto);
			res.addData(dto.getId());
			res.addMessage("Data Updated Successfully..!!");
		} else {
			long pk = customerService.add(dto);
			res.addData(pk);
			res.addMessage("Data added Successfully..!!");
		}
		return res;
	}
	
	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {

		ORSResponse res = new ORSResponse();

		CustomerDTO dto = customerService.findById(id);

		res.addData(dto);

		return res;
	}
	
	@GetMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable long[] ids) {

		ORSResponse res = new ORSResponse();

		for (long id : ids) {
			customerService.delete(id);
		}

		res.addMessage("data deleted successfully");

		return res;
	}
	
	@PostMapping("search/{pageNo}")
	public ORSResponse search(@RequestBody CustomerForm form, @PathVariable int pageNo) {

		ORSResponse res = new ORSResponse();

		CustomerDTO dto = (CustomerDTO) form.getDto();

		List list = customerService.search(dto, pageNo, 5);

		if (list.size() == 0) {
			res.addMessage("Result not found...!!!");
		} else {
			res.addData(list);
		}
		return res;
	}
	
	

}
