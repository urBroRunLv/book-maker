package edu.njucm.book.frame.controller.book;

import static java.util.Objects.nonNull;

import edu.njucm.book.common.constant.IPageConstant;
import edu.njucm.book.frame.domain.ReferenceInfo;
import edu.njucm.book.frame.service.IBookInfoService;
import edu.njucm.book.frame.service.IReferenceInfoService;

import edu.njucm.book.frame.util.ValidateUtils;
import edu.njucm.book.user.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author lvrongwang
 * @since 2020/5/20 10:38:55
 */
@Controller
@RequestMapping("reference")
public class ReferenceController extends BaseController implements IPageConstant {

    @Autowired
    private IBookInfoService bookInfoService;
    @Autowired
    private IReferenceInfoService referenceInfoService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listReference(Long bookId, Model model) {
        if (nonNull(bookId)) {
            model.addAttribute("book", bookInfoService.getBookInfoByBookId(bookId));
            model.addAttribute("references", referenceInfoService.listReferenceInfoByBookId(bookId));
            return "book/reference/reference";
        }
        return redirect(ERROR_403);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addReference(Long bookId, Model model) {
        if (nonNull(bookId)) {
            model.addAttribute("book", bookInfoService.getBookInfoByBookId(bookId));
            return "book/reference/add";
        }
        return redirect(ERROR_403);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addReferenceSubmit(ReferenceInfo referenceInfo, Model model) {
        if (!ValidateUtils.isReferenceInfoValidate(referenceInfo)) {
            model.addAttribute("referenceInfo", referenceInfo);
            return "book/reference/add";
        }
        model.addAttribute("book", bookInfoService.getBookInfoByBookId(referenceInfo.getBookId()));
        if (referenceInfoService.saveReferenceInfo(referenceInfo)) {
            return redirect("/reference/success?id=" + referenceInfo.getBookId());
        }
        return redirect(ERROR_403);
    }

    @RequestMapping(value = "/update/{referenceId}", method = RequestMethod.GET)
    public String updateReference(@PathVariable Long referenceId, Model model) {
        if (nonNull(referenceId)) {
            ReferenceInfo referenceInfo = referenceInfoService.getByReferenceId(referenceId);
            model.addAttribute("referenceInfo", referenceInfo);
            if (nonNull(referenceInfo)) {
                model.addAttribute("book", bookInfoService.getBookInfoByBookId(referenceInfo.getBookId()));
            }
            return "book/reference/update";
        }
        return redirect(ERROR_403);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateReferenceSubmit(ReferenceInfo referenceInfo, Model model) {
        if (!ValidateUtils.isReferenceInfoValidate(referenceInfo)) {
            model.addAttribute("referenceInfo", referenceInfo);
            return "book/reference/update";
        }
        model.addAttribute("book", bookInfoService.getBookInfoByBookId(referenceInfo.getBookId()));
        if (referenceInfoService.updateReferenceInfo(referenceInfo)) {
            return redirect("/success?id=" + referenceInfo.getBookId());
        }
        return redirect(ERROR_403);
    }

    @RequestMapping(value = "success", method = RequestMethod.GET)
    public String success(Long id, Model model) {
        model.addAttribute("id", id);
        return "book/reference/success";
    }
}
