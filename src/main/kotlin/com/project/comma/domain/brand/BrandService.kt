package com.project.comma.domain.brand


import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BrandService(
    private val brandRepository: BrandRepository
) {

  fun searchBrand(): List<Brand> {
    return brandRepository.findAll()
  }

}