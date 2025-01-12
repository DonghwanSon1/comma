package com.project.comma.domain.brand


import com.project.comma.common.exception.CommonException
import com.project.comma.common.exception.CommonExceptionCode
import com.project.comma.domain.brand.rqrs.BrandRq
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BrandService(
    private val brandRepository: BrandRepository
) {

  fun searchBrand(): List<Brand> {
    return brandRepository.findAll().sortedBy { it.name }
  }

  @Transactional
  fun saveBrand(rq: BrandRq): String {
    val entity: Brand = if (rq.sn != null) {
      val findBrandEntity = brandRepository.findById(rq.sn!!).orElseThrow { CommonException(CommonExceptionCode.DOES_NOT_EXIST_BRAND) }
      findBrandEntity.updateBrand(rq)
    } else {
      Brand.createBrand(rq)
    }

    brandRepository.save(entity)
    return if (rq.sn != null) { "정상적으로 수정 되었습니다." } else { "정상적으로 저장되었습니다." }
  }

  @Transactional
  fun deleteBrand(brandSn: Long): String {
    val entity: Brand = brandRepository.findById(brandSn).orElseThrow { CommonException(CommonExceptionCode.DOES_NOT_EXIST_BRAND) }
    brandRepository.delete(entity)
    return "정상적으로 삭제했습니다."
  }

}