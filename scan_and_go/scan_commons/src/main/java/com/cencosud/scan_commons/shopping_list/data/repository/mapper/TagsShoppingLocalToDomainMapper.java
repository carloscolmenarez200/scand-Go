package com.cencosud.scan_commons.shopping_list.data.repository.mapper;

import com.cencosud.scan_commons.shopping_list.data.local.TagsShoppingLocal;
import com.cencosud.scan_commons.shopping_list.domain.model.TagsShopping;
import com.core.data.repository.mapper.Mapper;
import javax.inject.Inject;

/**
 * Created by carlos on 19-07-18.
 */

public class TagsShoppingLocalToDomainMapper extends Mapper<TagsShoppingLocal, TagsShopping> {

    @Inject
    public TagsShoppingLocalToDomainMapper() {
    }

    @Override
    public TagsShopping map(TagsShoppingLocal value) {
        TagsShopping tagsShopping = new TagsShopping();
        tagsShopping.name = value.name;
        tagsShopping.checked = value.checked;
        return tagsShopping;
    }

    @Override
    public TagsShoppingLocal reverseMap(TagsShopping value) {
        TagsShoppingLocal tagsShoppingLocal = new TagsShoppingLocal();
        tagsShoppingLocal.name = value.name;
        tagsShoppingLocal.checked = value.checked;
        return tagsShoppingLocal;
    }
}
